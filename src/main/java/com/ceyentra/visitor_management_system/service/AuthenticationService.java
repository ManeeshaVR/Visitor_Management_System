package com.ceyentra.visitor_management_system.service;

import com.ceyentra.visitor_management_system.entity.User;
import com.ceyentra.visitor_management_system.reqAndresp.response.JwtAuthResponse;
import com.ceyentra.visitor_management_system.reqAndresp.secure.SignIn;
import com.ceyentra.visitor_management_system.reqAndresp.secure.SignUp;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthenticationService {

    JwtAuthResponse signIn(SignIn signIn);

    JwtAuthResponse signUp(SignUp signUp);

    JwtAuthResponse refreshToken(String accessToken);

}
