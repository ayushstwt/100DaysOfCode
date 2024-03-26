package com.narainox.journalApp.service;

import com.narainox.journalApp.entity.User;
import com.narainox.journalApp.exception.ResourceNotFound;
import com.narainox.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user)
    {
        return userRepository.save(user);
    }

    public User deleteUser(ObjectId myId)
    {
        User user = userRepository
                .findById(myId)
                .orElseThrow(() -> new ResourceNotFound("User", "UserId", myId));
        userRepository.delete(user);
        return user;
    }
    public User getUserById(ObjectId userId)
    {
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new ResourceNotFound("User", "UserId", userId));
        return user;
    }
    public User getUserByUsername(String username)
    {
        User user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new ResourceNotFound("User", "UserId", username));
        return user;
    }
    public List<User> getAllUser()
    {
        return userRepository.findAll();
    }
}
