package com.example.resens.service;

import com.example.resens.enumeration.Role;

import java.util.List;

public interface RoleService {
    Role saveRole(Role role);
    void deleteRole(Long id);
    Role getRoleById(Long id);
    List<Role> getAllRoles();
}
