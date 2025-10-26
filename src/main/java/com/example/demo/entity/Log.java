//package com.example.demo.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.CreationTimestamp;
//
//import java.time.Instant;
//
//@Entity
//@Builder
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class Log {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//    @Column(nullable = false)
//    String method;
//    @Column(nullable = false)
//    String url;
//    String requestBody;
//    @Column(nullable = false)
//    Long timeTaken;
//    @Column(nullable = false)
//    String responseBody;
//    @CreationTimestamp()
//    private Instant timestamp;
//
//}
