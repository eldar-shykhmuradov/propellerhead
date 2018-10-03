package com.elworld.propellerhead.models.response;

import com.elworld.propellerhead.models.dto.CustomerDto;
import lombok.Data;

import java.util.List;

@Data
public class CustomersResponse {

    private final List<CustomerDto> customers;
    private final int customersNumber;
}
