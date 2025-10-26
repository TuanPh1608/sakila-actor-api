package com.example.demo.service;


import com.example.demo.dto.film.FilmCreateRequest;
import com.example.demo.dto.film.FilmResponse;
import com.example.demo.dto.film.FilmUpdateRequest;
import com.example.demo.entity.Film;
import com.example.demo.entity.Rating;
import com.example.demo.exception.NotFoundException;
import com.example.demo.mapper.FilmMapper;
import com.example.demo.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class FilmService {

    private final FilmRepository filmRepository;
    private final FilmMapper filmMapper;

    // Lấy tất cả films
    @Transactional(readOnly = true)
    public List<FilmResponse> getAllFilms() {
        return filmRepository.findAll()
                .stream()
                .map(filmMapper::toResponse)
                .collect(Collectors.toList());
    }

    // Lấy films với phân trang
    @Transactional(readOnly = true)
    public Page<FilmResponse> getAllFilms(Pageable pageable) {
        return filmRepository.findAll(pageable)
                .map(filmMapper::toResponse);
    }

    // Lấy film theo ID
    @Transactional(readOnly = true)
    public FilmResponse getFilmById(Integer id) {
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found film with id" + id));
        return filmMapper.toResponse(film);
    }

    // Tạo film mới
    public FilmResponse createFilm(FilmCreateRequest request) {
        Film film = filmMapper.toEntity(request);
        Film savedFilm = filmRepository.save(film);
        return filmMapper.toResponse(savedFilm);
    }

    // Cập nhật film
    public FilmResponse updateFilm(Integer id, FilmUpdateRequest request) {
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found film with id" + id));

        filmMapper.updateEntity(film, request);
        Film updatedFilm = filmRepository.save(film);
        return filmMapper.toResponse(updatedFilm);
    }

    // Xóa film
    public void deleteFilm(Integer id) {
        if (!filmRepository.existsById(id)) {
            throw new NotFoundException("Not found film with id" + id);
        }
        filmRepository.deleteById(id);
    }

    // Tìm kiếm theo title
    @Transactional(readOnly = true)
    public List<FilmResponse> searchByTitle(String title) {
        return filmRepository.findByTitleContainingIgnoreCase(title)
                .stream()
                .map(filmMapper::toResponse)
                .collect(Collectors.toList());
    }

    // Tìm kiếm theo title với phân trang
    @Transactional(readOnly = true)
    public Page<FilmResponse> searchByTitle(String title, Pageable pageable) {
        return filmRepository.findByTitleContainingIgnoreCase(title, pageable)
                .map(filmMapper::toResponse);
    }

    // Lấy films theo rating
    @Transactional(readOnly = true)
    public List<FilmResponse> getFilmsByRating(Rating rating) {
        return filmRepository.findByRating(rating)
                .stream()
                .map(filmMapper::toResponse)
                .collect(Collectors.toList());
    }


}
