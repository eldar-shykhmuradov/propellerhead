package com.elworld.propellerhead.utils;

import com.elworld.propellerhead.db.tables.records.CustomerRecord;
import com.elworld.propellerhead.models.dto.CustomerDto;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ConvertUtil {

    public static List<CustomerDto> convertToCustomerDtos(List<CustomerRecord> records) {
        return records.stream().map(ConvertUtil::convertToCustomerDto).collect(Collectors.toList());
    }

    public static CustomerDto convertToCustomerDto(CustomerRecord record) {
        CustomerDto dto = new CustomerDto();
        dto.setId(record.getId());
        dto.setEmail(record.getEmail());
        dto.setName(record.getName());
        dto.setPhone(record.getPhone());
        dto.setStatus(record.getStatus());
        dto.setCreateTimestamp(record.getCreateTimestamp().toLocalDateTime());
        return dto;
    }
}
