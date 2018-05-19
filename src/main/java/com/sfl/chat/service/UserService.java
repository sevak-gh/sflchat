package com.sfl.chat.service;

import com.sfl.chat.domain.User;

/**
 * service interface for User.
 *
 * @author Sevak Gharibian
 */
public interface UserService extends BaseService<User> {
    public User findByUsername(String username);
}
