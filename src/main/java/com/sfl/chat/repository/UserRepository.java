package com.sfl.chat.repository;

import com.sfl.chat.domain.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.Repository;

/**
 * repository for User.
 *
 * @author Sevak Gharibian
 */
public interface UserRepository extends BaseRepository<User>, Repository<User, Long> {
    @Query("SELECT user FROM User user LEFT JOIN FETCH user.roles r LEFT JOIN FETCH r.permissions p WHERE user.username = :username")
    User findByUsername(@Param("username") String username);
}
