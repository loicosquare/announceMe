package com.announceMe.service;

import com.announceMe.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

    private final TokenRepository tokenRepository;

    @Override
    public void logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        jwt = authHeader.substring(7);
        var storedToken = tokenRepository.findByToken(jwt) //We find the token on the database in other to revoke it.
                .orElse(null);
        if (storedToken != null) {
            storedToken.setExpired(true); //We revoke the token on the database by setting the expired to true.
            storedToken.setRevoked(true); //We revoke the token on the database by setting the revoked to true.
            tokenRepository.save(storedToken); //We save the token on the database to update it.
            SecurityContextHolder.clearContext(); //We clear the security context to remove the user information's into the springSecurity Context.
        }
    }
}
