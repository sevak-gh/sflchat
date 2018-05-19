package com.sfl.chat.service;

import com.sfl.chat.domain.User;

import java.util.List;

/**
 * service interface for User.
 *
 * @author Sevak Gharibian
 */
public interface UserService extends BaseService<User> {
    public User findByUsername(String username);
    public List<User> findByRoleName(String roleName);
}
