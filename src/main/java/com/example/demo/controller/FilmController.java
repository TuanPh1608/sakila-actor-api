package com.example.demo.controller;

import com.example.demo.dto.film.FilmCreateRequest;
import com.example.demo.dto.film.FilmResponse;
import com.example.demo.dto.film.FilmUpdateRequest;
import com.example.demo.entity.Rating;
import com.example.demo.service.FilmService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/films")
@RequiredArgsConstructor
public class FilmController {
    private final FilmService filmService;

    // GET /api/films - Lấy tất cả films
    @GetMapping
    public ResponseEntity<List<FilmResponse>> getAllFilms() {
        List<FilmResponse> films = filmService.getAllFilms();
        return ResponseEntity.ok(films);
    }

    // GET /api/films/page - Lấy films với phân trang
    @GetMapping("/page")
    public ResponseEntity<Page<FilmResponse>> getAllFilmsPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "filmId") String sortBy,
            @RequestParam(defaultValue = "ASC") String direction) {

        Sort.Direction sortDirection = Sort.Direction.fromString(direction);
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));
        Page<FilmResponse> films = filmService.getAllFilms(pageable);
        return ResponseEntity.ok(films);
    }

    // GET /api/films/{id} - Lấy film theo ID
    @GetMapping("/{id}")
    public ResponseEntity<FilmResponse> getFilmById(@PathVariable Integer id) {
        FilmResponse film = filmService.getFilmById(id);
        return ResponseEntity.ok(film);
    }

    // POST /api/films - Tạo film mới
    @PostMapping
    public ResponseEntity<FilmResponse> createFilm(@Valid @RequestBody FilmCreateRequest request) {
        FilmResponse createdFilm = filmService.createFilm(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFilm);
    }

    // PUT /api/films/{id} - Cập nhật film
    @PutMapping("/{id}")
    public ResponseEntity<FilmResponse> updateFilm(
            @PathVariable Integer id,
            @RequestBody FilmUpdateRequest request) {
        FilmResponse updatedFilm = filmService.updateFilm(id, request);
        return ResponseEntity.ok(updatedFilm);
    }

    // DELETE /api/films/{id} - Xóa film
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable Integer id) {
        filmService.deleteFilm(id);
        return ResponseEntity.noContent().build();
    }

    // GET /api/films/search - Tìm kiếm theo title
    @GetMapping("/search")
    public ResponseEntity<List<FilmResponse>> searchByTitle(@RequestParam String title) {
        List<FilmResponse> films = filmService.searchByTitle(title);
        return ResponseEntity.ok(films);
    }

    // GET /api/films/search/page - Tìm kiếm theo title với phân trang
    @GetMapping("/search/page")
    public ResponseEntity<Page<FilmResponse>> searchByTitlePage(
            @RequestParam String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<FilmResponse> films = filmService.searchByTitle(title, pageable);
        return ResponseEntity.ok(films);
    }

    // GET /api/films/rating/{rating} - Lấy films theo rating
    @GetMapping("/rating/{rating}")
    public ResponseEntity<List<FilmResponse>> getFilmsByRating(@PathVariable Rating rating) {
        List<FilmResponse> films = filmService.getFilmsByRating(rating);
        return ResponseEntity.ok(films);
    }
}
