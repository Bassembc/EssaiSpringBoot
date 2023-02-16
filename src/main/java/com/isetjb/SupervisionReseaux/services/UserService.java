package com.isetjb.SupervisionReseaux.services;

import com.isetjb.SupervisionReseaux.entities.User;
import com.isetjb.SupervisionReseaux.repositories.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Data
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }
    public Optional<User> getUserConnected(String userName,String password){
        return userRepository.findUserByUserNameAndPassword(userName, password);
    }

    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    public void deleteUser( Long id) {
        userRepository.deleteById(id);
    }

    public User saveUser(User user) {
        User savedUser = userRepository.save(user);
        return savedUser;
    }
}
