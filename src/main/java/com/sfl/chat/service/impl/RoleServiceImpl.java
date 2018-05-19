package com.sfl.chat.service.impl;

import com.sfl.chat.domain.Role;
import com.sfl.chat.repository.RoleRepository;
import com.sfl.chat.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * service implementation for Role.
 *
 * @author Sevak Gharibian
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

  private final RoleRepository roleRepository;

  @Autowired
  public RoleServiceImpl(RoleRepository roleRepository) {
    super(roleRepository);
    this.roleRepository = roleRepository;
  }

    @Override
    @Transactional(readOnly = true)
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
