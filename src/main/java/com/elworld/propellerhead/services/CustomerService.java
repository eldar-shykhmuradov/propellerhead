package com.elworld.propellerhead.services;

import com.elworld.propellerhead.db.enums.CustomerStatus;
import com.elworld.propellerhead.db.tables.records.CustomerNoteRecord;
import com.elworld.propellerhead.db.tables.records.CustomerRecord;
import com.elworld.propellerhead.exceptions.CustomerNotExistsException;
import com.elworld.propellerhead.exceptions.CustomerNoteNotExistsException;
import com.elworld.propellerhead.exceptions.InvalidCustomerNoteAccessException;
import com.elworld.propellerhead.models.request.CustomerNoteRequest;
import com.elworld.propellerhead.models.request.CustomerRequest;
import com.elworld.propellerhead.models.response.CustomerNoteResponse;
import com.elworld.propellerhead.models.response.CustomerNotesResponse;
import com.elworld.propellerhead.models.response.CustomerResponse;
import com.elworld.propellerhead.models.response.CustomersResponse;
import com.elworld.propellerhead.models.enums.SortColumn;
import com.elworld.propellerhead.models.enums.SortDirection;
import com.elworld.propellerhead.repositories.CustomerNoteRepository;
import com.elworld.propellerhead.repositories.CustomerRepository;
import com.elworld.propellerhead.utils.ConvertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerNoteRepository customerNoteRepository;

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
        final CustomerRecord customerRecord = customerRepository.getCustomerById(customerId).orElseThrow(CustomerNotExistsException::new);
        final List<CustomerNoteRecord> customerNotes = customerNoteRepository.getCustomerNotes(customerRecord.getId(), number, count);
        if (CollectionUtils.isEmpty(customerNotes)) {
            return new CustomerNotesResponse(Collections.emptyList(), 0);
        }
        return new CustomerNotesResponse(ConvertUtil.convertToCustomerNoteDtos(customerNotes), 0);
    }

    public void deleteCustomerNote(Long customerId, Long noteId) {
        customerRepository.getCustomerById(customerId).orElseThrow(CustomerNotExistsException::new);
        final CustomerNoteRecord customerNoteRecord = getCustomerNoteWithAccessCheck(customerId, noteId);
        customerNoteRepository.delete(customerNoteRecord);
    }

    public CustomerNoteResponse createCustomerNote(Long customerId, CustomerNoteRequest request) {
        final CustomerNoteRecord noteRecord = customerNoteRepository.newRecord();
        updateCustomerNoteRecord(customerId, noteRecord, request);

        return new CustomerNoteResponse(ConvertUtil.convertToCustomerNoteDto(noteRecord));
    }

    public CustomerNoteResponse updateCustomerNote(Long customerId, Long noteId, CustomerNoteRequest request) {
        final CustomerNoteRecord noteRecord = getCustomerNoteWithAccessCheck(customerId, noteId);
        updateCustomerNoteRecord(customerId, noteRecord, request);

        return new CustomerNoteResponse(ConvertUtil.convertToCustomerNoteDto(noteRecord));
    }

    private void updateCustomerNoteRecord(Long customerId, CustomerNoteRecord record, CustomerNoteRequest request) {
        customerRepository.getCustomerById(customerId).orElseThrow(CustomerNotExistsException::new);

        record.setCustomerId(customerId);
        record.setText(request.getText());
        record.setTitle(request.getTitle());
        record.setCreateTimestamp(new Timestamp(System.currentTimeMillis()));
        customerNoteRepository.store(record);
    }

    private CustomerNoteRecord getCustomerNoteWithAccessCheck(Long customerId, Long noteId) {
        final CustomerNoteRecord customerNoteRecord = customerNoteRepository.getCustomerNoteById(noteId).orElseThrow(CustomerNoteNotExistsException::new);
        if (!customerNoteRecord.getCustomerId().equals(customerId)) {
            throw new InvalidCustomerNoteAccessException();
        }
        return customerNoteRecord;
    }
}
