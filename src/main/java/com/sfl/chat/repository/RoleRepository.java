package com.sfl.chat.repository;

import com.sfl.chat.domain.Role;

import org.springframework.data.repository.Repository;

/**
 * repository for Role.
 *
 * @author Sevak Gharibian
 */
public interface RoleRepository extends BaseRepository<Role>, Repository<Role, Long> {
    public Role findByName(String name);
}
