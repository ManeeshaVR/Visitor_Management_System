package com.ceyentra.visitor_management_system.reqAndresp.secure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SignUp {

    private Integer id;
    private String name;
    private String email;
    private String password;

}
