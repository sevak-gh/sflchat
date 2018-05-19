package com.sfl.chat.security;

import com.sfl.chat.dto.ErrorCodes;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import org.springframework.stereotype.Component;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.access.AccessDeniedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * handles access denied requests, 403 instead default contianer error message.
 *
 * @author Sevak Gharibian
 *
 */
@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {

    private static final Logger LOG = LoggerFactory.getLogger(RestAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String message = "access denied";
        if (accessDeniedException != null) {
            message = accessDeniedException.getMessage();
        }
        response.getWriter().write(String.format("{\"success\": %s, \"message\": \"%s\", \"code\": %d}", 
                                                    "false", message, ErrorCodes.ACCESS_DENIED));
    }
}
