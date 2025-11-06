package com.mottu.motolocation.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

public class ApiKeyAuthFilter extends OncePerRequestFilter {

    private final String apiKey;
    private static final String API_KEY_HEADER = "X-API-KEY";

    public ApiKeyAuthFilter(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String requestApiKey = request.getHeader(API_KEY_HEADER);

        if (Objects.equals(apiKey, requestApiKey)) {
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    "api-user",
                    null,
                    AuthorityUtils.createAuthorityList("ROLE_ADMIN")
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
            return;
        }

        // chave ausente ou inválida -> responde 401 JSON e interrompe a cadeia
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{\"error\":\"Unauthorized - invalid or missing API key\"}");
    }
}
