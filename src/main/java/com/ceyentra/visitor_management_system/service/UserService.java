package com.ceyentra.visitor_management_system.service;

import com.ceyentra.visitor_management_system.reqAndresp.secure.SignUp;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

    UserDetailsService userDetailsService();

    void save(SignUp user);

}
