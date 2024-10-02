package com.ceyentra.visitor_management_system.dto;

import com.ceyentra.visitor_management_system.entity.Building;
import com.ceyentra.visitor_management_system.enums.Floor;
import jakarta.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDTO {

    private Integer id;
    private String name;
    @Pattern(regexp = "^(?:0|94|\\+94|0094)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|4|5|6|7|8)\\d)\\d{6}$", message = "Invalid mobile number format")
    private String phoneNo;
    @Pattern(regexp = "(^[a-zA-Z0-9_.-]+)@([a-zA-Z]+)([\\.])([a-zA-Z]+)$", message = "Invalid email format")
    private String email;
    private String position;
    private String status;
    private Building building;
    private Floor floor;

}
