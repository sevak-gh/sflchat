package com.sfl.chat.service;

import com.sfl.chat.domain.Role;

/**
 * service interface for Role.
 *
 * @author Sevak Gharibian
 */
public interface RoleService extends BaseService<Role> {
    public Role findByName(String name);
}
