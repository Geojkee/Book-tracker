package com.dwtd.book_tracker.bookTracker.Exception.Auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException
    ) throws IOException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        String body = """
                {
                  "timestamp": "%s",
                  "status": 403,
                  "error": "Forbidden",
                  "message": "Access denied",
                  "path": "%s"
                }
                """.formatted(Instant.now(), request.getRequestURI());

        response.getWriter().write(body);
    }
}
