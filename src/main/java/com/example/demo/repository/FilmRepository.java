package com.example.demo.repository;

import com.example.demo.entity.Film;
import com.example.demo.entity.Rating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Integer> {
    // Tìm theo title
    List<Film> findByTitleContainingIgnoreCase(String title);

    // Tìm theo title với phân trang
    Page<Film> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    // Tìm theo rating
    List<Film> findByRating(Rating rating);

    // Tìm theo language
    List<Film> findByLanguageId(Short languageId);

    // Tìm film có rental rate trong khoảng
    @Query("SELECT f FROM Film f WHERE f.rentalRate BETWEEN :minRate AND :maxRate")
    List<Film> findByRentalRateBetween(@Param("minRate") java.math.BigDecimal minRate,
                                       @Param("maxRate") java.math.BigDecimal maxRate);

    // Đếm số film theo rating
    long countByRating(Rating rating);
}
