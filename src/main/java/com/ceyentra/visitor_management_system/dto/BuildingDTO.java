package com.ceyentra.visitor_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BuildingDTO {

    private int buildingId;
    private String name;
    private String address;

}
