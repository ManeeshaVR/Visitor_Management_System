package com.ceyentra.visitor_management_system.service.impl;

import com.ceyentra.visitor_management_system.dao.UserDAO;
import com.ceyentra.visitor_management_system.entity.User;
import com.ceyentra.visitor_management_system.reqAndresp.secure.SignUp;
import com.ceyentra.visitor_management_system.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserServiceImpl(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public UserDetailsService userDetailsService() {

        return username -> {
            logger.info("Attempting to load user by username: {}", username);
            return (UserDetails) userDAO.findByUserName(username)
                    .orElseThrow(() -> {
                        logger.warn("User not found with username: {}", username);
                        return new UsernameNotFoundException("User not found");
                    });
        };
    }

    public void save(SignUp user) {
        logger.info("Attempting to save user with email: {}", user.getEmail());
        User tempUser = new User(user.getId(), user.getName(), user.getEmail(), user.getPassword());
        userDAO.save(tempUser);
        logger.info("User saved successfully with email: {}", user.getEmail());
    }

}
