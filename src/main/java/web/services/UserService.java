package web.services;

import web.models.User;

import java.util.List;

public interface UserService {

    List<User> listUsers();

    User show(Long id);

    void save(User user);

    void update(User updatedUser);

    void delete(Long id);

}
