package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> listUsers() {
        return entityManager.createQuery("SELECT u from User u", User.class).getResultList();
    }

    @Override
    public User show(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findByUsername(String name) {
        return entityManager.createQuery(
                "SELECT u from User u WHERE u.username = :username", User.class).
                setParameter("username", name).getSingleResult();
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void update(User updatedUser) {
        entityManager.merge(updatedUser);
    }

    @Override
    public void delete(User user) {
        entityManager.remove(user);
    }
}