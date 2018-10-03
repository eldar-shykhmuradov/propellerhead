package com.elworld.propellerhead.models.response;

import com.elworld.propellerhead.models.dto.CustomerDto;
import lombok.Data;

import java.util.List;

@Data
public class CustomerResponse {

    private final CustomerDto customer;
}
