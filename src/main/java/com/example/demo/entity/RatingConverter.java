package com.example.demo.entity;

import com.example.demo.entity.Rating;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true) // autoApply = true sẽ tự động áp dụng converter này cho tất cả các thuộc tính có kiểu Rating
public class RatingConverter implements AttributeConverter<Rating, String> {

    /**
     * Chuyển đổi từ Enum (Rating.NC_17) sang chuỗi để lưu vào DB ("NC-17").
     */
    @Override
    public String convertToDatabaseColumn(Rating rating) {
        if (rating == null) {
            return null;
        }
        return rating.getValue();
    }

    /**
     * Chuyển đổi từ chuỗi trong DB ("NC-17") sang Enum (Rating.NC_17).
     */
    @Override
    public Rating convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }

        // Dùng Stream để tìm enum tương ứng với giá trị chuỗi
        return Stream.of(Rating.values())
                .filter(r -> r.getValue().equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new); // Ném exception nếu không tìm thấy
    }
}