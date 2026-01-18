package com.shobby.role.repository;

import com.shobby.role.entity.Role;
import com.shobby.role.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByRoleType(RoleType roleType);
}
