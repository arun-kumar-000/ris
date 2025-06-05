package com.live.ris.services;

import com.live.ris.entities.User;
import com.live.ris.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userDetailsRepository;

    public User authenticate(String userName, String pass) {
    	User user = userDetailsRepository.findByUserName(userName).get();
        if (user != null && user.getPass().equals(pass)) {
            return user;
        }
        return null;
    }
}
