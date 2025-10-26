package com.example.demo.dto.film;

import com.example.demo.entity.Film;
import com.example.demo.entity.Rating;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Year;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmUpdateRequest {

    @Size(max = 255, message = "Title must not exceed 255 characters")
    private String title;

    @Size(max = 65535, message = "Description is too long")
    private String description;

    private Year releaseYear;

    @Positive(message = "Language ID must be positive")
    private Short languageId;

    @Positive(message = "Original Language ID must be positive")
    private Short originalLanguageId;

    @Min(value = 1, message = "Rental duration must be at least 1 day")
    @Max(value = 255, message = "Rental duration must not exceed 255 days")
    private Short rentalDuration;

    @DecimalMin(value = "0.00", message = "Rental rate must be non-negative")
    @DecimalMax(value = "99.99", message = "Rental rate must not exceed 99.99")
    @Digits(integer = 2, fraction = 2, message = "Rental rate must have at most 2 integer digits and 2 decimal digits")
    private BigDecimal rentalRate;

    @Min(value = 1, message = "Length must be at least 1 minute")
    @Max(value = 65535, message = "Length must not exceed 65535 minutes")
    private Integer length;

    @DecimalMin(value = "0.00", message = "Replacement cost must be non-negative")
    @DecimalMax(value = "999.99", message = "Replacement cost must not exceed 999.99")
    @Digits(integer = 3, fraction = 2, message = "Replacement cost must have at most 3 integer digits and 2 decimal digits")
    private BigDecimal replacementCost;

    private Rating rating;

    private String specialFeatures;
}