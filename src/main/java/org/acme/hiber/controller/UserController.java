package org.acme.hiber.controller;

import java.util.List;

import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.acme.hiber.controller.dto.UserDTO;
import org.acme.hiber.model.User;

@Singleton
public class UserController {
    public List<User> findAllUsers() {
        return User.findAll().list();
    }

    public User findUser(Long id) {
        return User.findById(id);
    }

    @Transactional
    public User save(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.persist();
        return user;
    }
}
