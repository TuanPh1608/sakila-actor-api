package com.example.demo.config;

//import com.example.demo.entity.Log;
//import com.example.demo.service.LogService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Component
public class LoggerFilter extends OncePerRequestFilter {
//    private final LogService logService;

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerFilter.class);
//
//    public LoggerFilter(LogService logService) {
//        this.logService = logService;
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        ContentCachingRequestWrapper requestWrapper = wrapRequest(request);
        ContentCachingResponseWrapper responseWrapper = wrapResponse(response);

        long startTime = System.currentTimeMillis();
        try {

            filterChain.doFilter(requestWrapper, responseWrapper);
        } finally {
            long timeTaken = System.currentTimeMillis() - startTime;

            // Log Request và Response
            logRequest(requestWrapper);
            logResponse(responseWrapper, timeTaken);

            responseWrapper.copyBodyToResponse();

//            Log log = Log.builder()
//                    .method(requestWrapper.getMethod())
//                    .url(requestWrapper.getRequestURI())
//                    .requestBody(getStringValue(requestWrapper.getContentAsByteArray(), requestWrapper.getCharacterEncoding()))
//                    .timeTaken(timeTaken)
//                    .responseBody(getStringValue(responseWrapper.getContentAsByteArray(), responseWrapper.getCharacterEncoding()))
//                    .build();

//            logService.save(log);
        }
    }

    private void logRequest(ContentCachingRequestWrapper request) {
        LOGGER.info("👉 REQUEST: Method=[{}] URI=[{}] Content-Type=[{}]",
                request.getMethod(),
                request.getRequestURI(),
                request.getContentType());

        String body = getStringValue(request.getContentAsByteArray(), request.getCharacterEncoding());
        if (!body.isEmpty()) {
            LOGGER.info("📝 REQUEST BODY: {}", body.replaceAll("\\n", "")); // Xóa xuống dòng cho gọn log
        }
    }

    private void logResponse(ContentCachingResponseWrapper response, long timeTaken) {
        LOGGER.info("👈 RESPONSE: Status=[{}] TimeTaken=[{}ms] Content-Type=[{}]",
                response.getStatus(),
                timeTaken,
                response.getContentType());

        // Log response body
        String body = getStringValue(response.getContentAsByteArray(), response.getCharacterEncoding());
        if (!body.isEmpty()) {
            LOGGER.info("📦 RESPONSE BODY: {}", body);
        }
    }

    private ContentCachingRequestWrapper wrapRequest(HttpServletRequest request) {
        if (request instanceof ContentCachingRequestWrapper) {
            return (ContentCachingRequestWrapper) request;
        }
        return new ContentCachingRequestWrapper(request);
    }

    private ContentCachingResponseWrapper wrapResponse(HttpServletResponse response) {
        if (response instanceof ContentCachingResponseWrapper) {
            return (ContentCachingResponseWrapper) response;
        }
        return new ContentCachingResponseWrapper(response);
    }

    private String getStringValue(byte[] contentAsByteArray, String characterEncoding) {
        try {
            return new String(contentAsByteArray, 0, contentAsByteArray.length, characterEncoding != null ? characterEncoding : "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}