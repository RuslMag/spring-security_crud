package web.dao;

import web.models.User;

import java.util.List;


public interface UserDao {

    List<User> listUsers();

    User show(Long id);

    User findByUsername(String name);

    void save(User user);

    void update(User updatedUser);

    void delete(User user);
}
