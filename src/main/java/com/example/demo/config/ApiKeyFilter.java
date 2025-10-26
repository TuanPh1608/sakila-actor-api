//package com.example.demo.config;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//@Slf4j
//public class ApiKeyFilter extends OncePerRequestFilter {
//
//    @Value("${apiKey.secretKey}")
//    private String VALID_API_KEY;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String apiKey = request.getHeader("X-Api-Key");
//        log.debug(VALID_API_KEY);
//        log.debug(apiKey);
//        if(VALID_API_KEY.equals(apiKey)){
//            filterChain.doFilter(request,response);
//        }
//        else{
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//        }
//
//    }
//}
