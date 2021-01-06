package web.dao;

import web.models.Role;

import java.util.Set;

public interface RoleDao {
    Set<Role> getRoles(Set<String> role);

    Role getDefaultRole();

    Role getRoleByName(String name);
}
