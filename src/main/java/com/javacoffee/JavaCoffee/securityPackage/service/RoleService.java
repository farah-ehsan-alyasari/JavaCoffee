package com.javacoffee.JavaCoffee.securityPackage.service;

import com.javacoffee.JavaCoffee.securityPackage.entity.Role;

import java.util.List;

/**
 * @author Igor Adulyan
 */
public interface RoleService {
    public void saveRole(Role role);
    public Role findRoleByRoleName(String name);
    public List<Role> getAllRoles();
    public List<Role> getRolesByUser(long id);
}