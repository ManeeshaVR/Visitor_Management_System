package com.ceyentra.visitor_management_system.service.impl;

import com.ceyentra.visitor_management_system.dao.UserDAO;
import com.ceyentra.visitor_management_system.entity.User;
import com.ceyentra.visitor_management_system.reqAndresp.response.JwtAuthResponse;
import com.ceyentra.visitor_management_system.reqAndresp.secure.SignIn;
import com.ceyentra.visitor_management_system.reqAndresp.secure.SignUp;
import com.ceyentra.visitor_management_system.service.AuthenticationService;
import com.ceyentra.visitor_management_system.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService{

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    private UserDAO userDAO;
    private JWTService jwtService;
    private PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationServiceImpl(UserDAO userDAO, JWTService jwtService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager){
        this.userDAO = userDAO;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }


    public JwtAuthResponse signIn(SignIn signIn) {
        logger.info("Attempting to authenticate user: {}", signIn.getEmail());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signIn.getEmail(), signIn.getPassword()));
        User userByEmail = userDAO.findByUserName(signIn.getEmail())
                .orElseThrow(() -> {
                    logger.warn("User not found: {}", signIn.getEmail());
                    return new UsernameNotFoundException("User not found");
                });
        var generatedToken = jwtService.generateToken((UserDetails) userByEmail);
        logger.info("Token generated for user: {}", signIn.getEmail());



        logger.info("User signed in: {}", signIn.getEmail());

        // Return response with token and branch name
        return JwtAuthResponse.builder()
                .token(generatedToken)
                .build();
    }

    public JwtAuthResponse signUp(SignUp signUp) {
        logger.info("Attempting to sign up user: {}", signUp.getEmail());
        boolean opt = userDAO.existsByUserName(signUp.getEmail());

        if (opt) {
            logger.warn("User already exists: {}", signUp.getEmail());
            return JwtAuthResponse.builder().token("User already exists").build();
        } else {
            var buildUser = User.builder()
                    .userId(signUp.getId())
                    .name(signUp.getName())
                    .userName(signUp.getEmail())
                    .password(passwordEncoder.encode(signUp.getPassword()))
                    .build();
            var savedUser = userDAO.save(buildUser);


            var genToken = jwtService.generateToken(savedUser);



            logger.info("User signed up: {},", signUp.getEmail());

            // Return response with token and branch name
            return JwtAuthResponse.builder()
                    .token(genToken)
                    .build();
        }
    }

    public JwtAuthResponse refreshToken(String accessToken) {
        logger.info("Attempting to refresh token for access token: {}", accessToken);
        var userName = jwtService.extractUsername(accessToken);
        var userEntity = userDAO.findByUserName(userName).orElseThrow(() -> {
            logger.warn("User not found: {}", userName);
            return new UsernameNotFoundException("User not found");
        });
        var refreshToken = jwtService.generateToken( userEntity);
        logger.info("Token refreshed for user: {}", userName);

        logger.info("Token refreshed for user: {},", userName);

        // Return response with token and branch name
        return JwtAuthResponse.builder()
                .token(refreshToken)
                .build();
    }

}
