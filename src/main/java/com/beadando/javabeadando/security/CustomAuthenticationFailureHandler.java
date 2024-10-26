package com.beadando.javabeadando.security; // Adjust according to your package structure

import jakarta.servlet.ServletException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        // Logic to handle failed authentication
        if (exception instanceof BadCredentialsException) {
            System.out.println("Login failed: Invalid username or password.");
        } else {
            System.out.println("Login failed: " + exception.getMessage());
        }

        // Redirect back to the login page (or handle it differently)
        response.sendRedirect(request.getContextPath() + "/login?error");
    }

    @Override
    public void onAuthenticationFailure(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

    }
}
