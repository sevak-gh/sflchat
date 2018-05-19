package com.sfl.chat.security;

import com.sfl.chat.dto.ErrorCodes;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import org.springframework.stereotype.Component;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * handles sodexo unauthenticated request, 401 instead of redirect.
 *
 * @author Sevak Gharibian
 *
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger LOG = LoggerFactory.getLogger(RestAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) 
                        throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("WWW-Authenticate", "Bearer");
        String message = "";
        if (authException instanceof InsufficientAuthenticationException) {
            message = "insufficient credentials";
        } else {
            message = authException.getMessage();
        }
        response.getWriter().write(String.format("{\"success\": %s, \"message\": \"%s\", \"code\": %d}", 
                                                    "false", message, ErrorCodes.NOT_AUTHENTICATED));
    }
}
