package com.ceyentra.visitor_management_system.rest;

import com.ceyentra.visitor_management_system.entity.User;
import com.ceyentra.visitor_management_system.reqAndresp.response.JwtAuthResponse;
import com.ceyentra.visitor_management_system.reqAndresp.secure.SignIn;
import com.ceyentra.visitor_management_system.reqAndresp.secure.SignUp;
import com.ceyentra.visitor_management_system.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthRestController {

    private AuthenticationService authenticationService;
    private static final Logger logger = LoggerFactory.getLogger(AuthRestController.class);

    @Autowired
    public AuthRestController(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signIn")
    public ResponseEntity<JwtAuthResponse> signIn(@RequestBody SignIn signIn) {
        logger.info("SignIn request received for user: {}", signIn.getEmail());
        try {
            JwtAuthResponse response = authenticationService.signIn(signIn);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error during signIn: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/signUp")
    public ResponseEntity<JwtAuthResponse> signUp(@RequestBody SignUp signUpReq) {
        logger.info("SignUp request received for user: {}", signUpReq.getEmail());
        try {
            JwtAuthResponse response = authenticationService.signUp(signUpReq);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error during signUp: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthResponse> refreshToken(@RequestParam("refreshToken") String refreshToken) {
        logger.info("Refresh token request received");
        try {
            JwtAuthResponse response = authenticationService.refreshToken(refreshToken);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error during token refresh: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
