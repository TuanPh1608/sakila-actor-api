package com.example.demo.dto.film;

import com.example.demo.entity.Rating;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmResponse {
    private Integer filmId;
    private String title;
    private String description;
    private Year releaseYear;
    private Short languageId;
    private Short originalLanguageId;
    private Short rentalDuration;
    private BigDecimal rentalRate;
    private Integer length;
    private BigDecimal replacementCost;
    private Rating rating;
    private String specialFeatures;
    private LocalDateTime lastUpdate;
}
