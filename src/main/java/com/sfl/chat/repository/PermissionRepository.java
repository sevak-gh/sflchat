package com.sfl.chat.repository;

import com.sfl.chat.domain.Permission;

import org.springframework.data.repository.Repository;

/**
 * repository for Permission.
 *
 * @author Sevak Gharibian
 */
public interface PermissionRepository extends BaseRepository<Permission>, Repository<Permission, Long> {
}
