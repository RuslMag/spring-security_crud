package web.dao;

import org.springframework.stereotype.Repository;
import web.models.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Set;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Set<Role> getRoles(Set<String> role) {
        return new HashSet<>(entityManager.createQuery("SELECT r FROM Role r WHERE r.name in (:roles)")
                .setParameter("roles", role)
                .getResultList());
    }

    @Override
    public Role getDefaultRole() {
        return getRoleByName("ROLE_USER");
    }

    @Override
    public Role getRoleByName(String name) {
        return entityManager.createQuery("SELECT r FROM Role r WHERE r.name = :roleName", Role.class)
                .setParameter("roleName", name)
                .getSingleResult();
    }
}
