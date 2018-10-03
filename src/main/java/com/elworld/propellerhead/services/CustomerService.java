package com.elworld.propellerhead.services;

import com.elworld.propellerhead.db.enums.CustomerStatus;
import com.elworld.propellerhead.db.tables.CustomerNote;
import com.elworld.propellerhead.db.tables.records.CustomerNoteRecord;
import com.elworld.propellerhead.db.tables.records.CustomerRecord;
import com.elworld.propellerhead.exceptions.CustomerNotExistsException;
import com.elworld.propellerhead.models.request.CustomerNoteRequest;
import com.elworld.propellerhead.models.request.CustomerRequest;
import com.elworld.propellerhead.models.response.CustomerNotesResponse;
import com.elworld.propellerhead.models.response.CustomerResponse;
import com.elworld.propellerhead.models.response.CustomersResponse;
import com.elworld.propellerhead.models.enums.SortColumn;
import com.elworld.propellerhead.models.enums.SortDirection;
import com.elworld.propellerhead.repositories.CustomerRepository;
import com.elworld.propellerhead.utils.ConvertUtil;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomersResponse getCustomers(CustomerStatus status, String text, SortColumn sortColumn, SortDirection sortDirection, Integer number, Integer count) {
        final List<CustomerRecord> customers = customerRepository.getCustomers(status, text, sortColumn, sortDirection, number, count);
        if (CollectionUtils.isEmpty(customers)) {
            return new CustomersResponse(Collections.emptyList(), 0);
        }
        return new CustomersResponse(ConvertUtil.convertToCustomerDtos(customers), 0);
    }

    public CustomerResponse updateCustomer(Long customerId, CustomerRequest request) {
        final CustomerRecord customerRecord = customerRepository.getCustomerById(customerId).orElseThrow(CustomerNotExistsException::new);
        customerRecord.setEmail(request.getEmail());
        customerRecord.setName(request.getName());
        customerRecord.setPhone(request.getPhone());
        customerRecord.setStatus(request.getStatus());
        customerRepository.store(customerRecord);

        return new CustomerResponse(ConvertUtil.convertToCustomerDto(customerRecord));
    }

    public CustomerNotesResponse getCustomerNotes(Long customerId, Integer number, Integer count) {
        return null;
    }

    public void deleteCustomerNote(Long customerId, Long noteId) {

    }

    public CustomerNotesResponse createCustomerNote(Long customerId, CustomerNoteRequest request) {
        return null;
    }

    public CustomerNote updateCustomerNote(Long customerId, Long noteId, CustomerNoteRequest request) {
        return null;
    }
}
