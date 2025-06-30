package com.farhan.serviceapp.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.farhan.serviceapp.auth.repository.authRepository;
import com.farhan.serviceapp.shared.service.JwtService;

import org.springframework.lang.NonNull;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final authRepository userRepository;

    @Override
    protected boolean shouldNotFilter(@NonNull HttpServletRequest request) {
        String path = request.getServletPath();

        // ✅ Only skip filtering for public auth endpoints
        return path.equals("/api/auth/login") ||
               path.equals("/api/auth/register") ||
               path.equals("/api/auth/request-password-reset-otp") ||
               path.equals("/api/auth/verify-otp") ||
               path.equals("/api/auth/reset-password") ||
               path.equals("/api/auth/test-mail");
    }
@Override
protected void doFilterInternal(
        @NonNull HttpServletRequest request,
        @NonNull HttpServletResponse response,
        @NonNull FilterChain filterChain
) throws ServletException, IOException {

    final String authHeader = request.getHeader("Authorization");

    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        System.out.println("⛔ Missing or invalid Authorization header");
        filterChain.doFilter(request, response);
        return;
    }

    final String jwt = authHeader.substring(7);
    String userEmail = null;

    try {
        userEmail = jwtService.extractUsername(jwt);
        System.out.println("✅ Extracted email from token: " + userEmail);
    } catch (Exception e) {
        System.out.println("❌ Error extracting email from token: " + e.getMessage());
        filterChain.doFilter(request, response);
        return;
    }

    if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        var user = userRepository.findByEmail(userEmail).orElse(null);

        if (user == null) {
            System.out.println("❌ User not found in DB for email: " + userEmail);
        }

    if (user != null && jwtService.isTokenValid(jwt, userEmail)) {
    var authorities = user.getAuthorities(); // ✅ use from UserDetails
    var authToken = new UsernamePasswordAuthenticationToken(
            user,
            null,
            authorities
    );

    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(authToken);
    System.out.println("✅ Security context set for user: " + userEmail);
}
 else {
            System.out.println("❌ Token invalid for user: " + userEmail);
        }
    }

    filterChain.doFilter(request, response);
}

}
