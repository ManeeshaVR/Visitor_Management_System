package com.ceyentra.visitor_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardDTO {

    private int cardId;
    private Integer cardNo;
    private String status;

}
