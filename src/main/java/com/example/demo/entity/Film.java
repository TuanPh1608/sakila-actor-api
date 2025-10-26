package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;

@Entity
@Table(name = "film", indexes = {
        @Index(name = "idx_title", columnList = "title"),
        @Index(name = "idx_fk_language_id", columnList = "language_id"),
        @Index(name = "idx_fk_original_language_id", columnList = "original_language_id")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id", columnDefinition = "SMALLINT UNSIGNED")
    private Integer filmId;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "release_year", columnDefinition = "YEAR")
    private Year releaseYear;

    @Column(name = "language_id", nullable = false, columnDefinition = "TINYINT UNSIGNED")
    private Byte languageId;

    @Column(name = "original_language_id", columnDefinition = "TINYINT UNSIGNED")
    private Byte originalLanguageId;

    @Column(name = "rental_duration", nullable = false, columnDefinition = "TINYINT UNSIGNED DEFAULT 3")
    private Byte rentalDuration = 3;

    @Column(name = "rental_rate", nullable = false, precision = 4, scale = 2, columnDefinition = "DECIMAL(4,2) DEFAULT 4.99")
    private BigDecimal rentalRate = new BigDecimal("4.99");

    @Column(name = "length", columnDefinition = "SMALLINT UNSIGNED")
    private Integer length;

    @Column(name = "replacement_cost", nullable = false, precision = 5, scale = 2, columnDefinition = "DECIMAL(5,2) DEFAULT 19.99")
    private BigDecimal replacementCost = new BigDecimal("19.99");

    @Column(name = "rating", columnDefinition = "ENUM('G','PG','PG-13','R','NC-17') DEFAULT 'G'")
    private Rating rating = Rating.G;

    @Column(name = "special_features", columnDefinition = "SET('Trailers','Commentaries','Deleted Scenes','Behind the Scenes')")
    private String specialFeatures;

    @UpdateTimestamp
    @Column(name = "last_update", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime lastUpdate;

}