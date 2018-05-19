package com.sfl.chat.security;

import com.sfl.chat.domain.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * custom authentication provider to handle remote ip address.
 *
 * @author Sevak Gharibian
 *
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private static final Logger LOG = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

    private final UserDetailsService userDetailsService;

    @Autowired
    public CustomAuthenticationProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication)  {
        String username = authentication.getName();
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        String password = (String)authentication.getCredentials();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);        
        if (userDetails == null) {  
            throw new BadCredentialsException("username not found");     
        }
        if (!bcrypt.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("password incorrect");
        } 
        if (!userDetails.isAccountNonExpired()) {
            throw new CredentialsExpiredException("account expired");
        }
        if (!userDetails.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException("password expired");
        }
        if (!userDetails.isAccountNonLocked()) {
            throw new LockedException("account locked");
        }
        if (!userDetails.isEnabled()) {
            throw new DisabledException("account disabled");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
