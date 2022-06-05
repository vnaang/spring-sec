package ru.dao;



import ru.models.User;

import java.util.List;

public interface UserDao {
    void add(User user);

    void removeUserById(Long id);

    List<User> getAllUsers();

    User getUserById(Long id);

    void updateUser(User user);

    User getUserByUserName(String userName);
}
