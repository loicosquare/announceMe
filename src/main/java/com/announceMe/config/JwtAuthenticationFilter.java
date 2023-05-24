package com.announceMe.config;

import java.io.IOException;

import com.announceMe.repository.TokenRepository;
import com.announceMe.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        if (request.getServletPath().contains("/api/v1/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        //According to schema the first thing we need to do on JwtFilter is to check if the token is present on the header and if it's valid.
        final String authHeader = request.getHeader("Authorization"); //The header is a part of our request.
        final String jwt;
        final String userEmail;

        //According to the JWT spec, the token should start with "Bearer "
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return; //We don't want to continue with the rest of operations.
        }

        //In this part we are trying to extract the token from the authenticated or authorized header.
        jwt = authHeader.substring(7); // Seven characters for the bearer and the space.
        userEmail = jwtService.extractUsername(jwt); //We extract also the username from the JWT.
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) { //Means that the user is not authenticated yet.
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail); //We can instantiate a new User too because User implements UserDetails interface.
            //Here we find the user by the token, and we map it to have a boolean value.
            //When we map we check if the token is not expired or revoked.
            var isTokenValid = tokenRepository.findByToken(jwt)
                    .map(t -> !t.isExpired() && !t.isRevoked())
                    .orElse(false);
            if (jwtService.isTokenValid(jwt, userDetails) && isTokenValid) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}