package com.sfl.chat.service.impl;

import com.sfl.chat.domain.User;
import com.sfl.chat.domain.Role;
import com.sfl.chat.domain.Permission;
import com.sfl.chat.repository.UserRepository;
import com.sfl.chat.service.UserService;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * service implementation for User.
 *
 * @author Sevak Gharibian
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService, UserDetailsService {
    
    private final UserRepository userRepository;
    
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("username[" + username + "] not found");
        }

        if ((user.getExpirationDate() != null) && (LocalDateTime.now().isAfter(user.getExpirationDate()))) {
            user.setAccountNonExpired(false);
        }

        for (Role role : user.getRoles()) {
            for (Permission permission : role.getPermissions()) {
                user.getAuthorities().add(new SimpleGrantedAuthority(permission.getName()));
            }
        }

        // credentialsNonExpired, accountNonLocked not used yet

        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findByRoleName(String roleName) {
        return userRepository.findByRolesNameAndEnabledTrue(roleName);
    }

}
