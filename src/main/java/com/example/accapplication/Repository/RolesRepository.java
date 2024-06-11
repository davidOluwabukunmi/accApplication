package com.example.accapplication.Repository;

import com.example.accapplication.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles, Integer> {
    Optional<Roles> findByAuthority (String authority);

        Optional<Roles> findRolesByRoleId (Integer roleId);
}
