package com.elworld.propellerhead.models.dto;

import com.elworld.propellerhead.db.enums.CustomerStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CustomerDto {

    private Long id;
    private CustomerStatus status;
    private LocalDateTime createTimestamp;
    private String name;
    private String email;
    private String phone;

}
