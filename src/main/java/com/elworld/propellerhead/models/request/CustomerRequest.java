package com.elworld.propellerhead.models.request;

import com.elworld.propellerhead.db.enums.CustomerStatus;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Data
public class CustomerRequest {

    @NotNull
    private CustomerStatus status;
    @NotBlank
    private String name;
    private String email;
    private String phone;
}
