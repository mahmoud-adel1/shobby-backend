package com.shobby.role.service;

import com.shobby.role.entity.Role;
import com.shobby.role.enums.RoleType;
import com.shobby.role.exception.RoleNotFoundException;
import com.shobby.role.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role findByRoleType(RoleType roleType) {
        return roleRepository.findByRoleType(roleType)
                .orElseThrow(()->new RoleNotFoundException("ROLE " + roleType.name() + " is not found!"));
    }
}
