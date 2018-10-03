package com.elworld.propellerhead.controllers;

import com.elworld.propellerhead.db.enums.CustomerStatus;
import com.elworld.propellerhead.models.enums.SortColumn;
import com.elworld.propellerhead.models.enums.SortDirection;
import com.elworld.propellerhead.models.request.CustomerNoteRequest;
import com.elworld.propellerhead.models.request.CustomerRequest;
import com.elworld.propellerhead.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<Object> getCustomers(@RequestParam(value = "status", required = false) CustomerStatus status,
                                               @RequestParam(value = "text", required = false) String text,
                                               @RequestParam(value = "sortColumn", defaultValue = "DATE") SortColumn sortColumn,
                                               @RequestParam(value = "sortDirection", defaultValue = "DESC") SortDirection sortDirection,
                                               @RequestParam(value = "number", defaultValue = "0") Integer number,
                                               @RequestParam(value = "count", defaultValue = "10") Integer count) {
        return new ResponseEntity<>(customerService.getCustomers(status, text, sortColumn, sortDirection, number, count), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCustomer(@PathVariable("id") Long customerId,
                                                 @Valid @RequestBody CustomerRequest request) {
        return new ResponseEntity<>(customerService.updateCustomer(customerId, request), HttpStatus.OK);
    }

    @GetMapping("/{id}/notes")
    public ResponseEntity<Object> getCustomerNotes(@PathVariable("id") Long customerId,
                                                   @RequestParam(value = "number", defaultValue = "0") Integer number,
                                                   @RequestParam(value = "count", defaultValue = "10") Integer count) {
        return new ResponseEntity<>(customerService.getCustomerNotes(customerId, number, count), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/notes/{noteId}")
    public ResponseEntity<String> deleteCustomerNote(@PathVariable("id") Long customerId,
                                                   @PathVariable("noteId") Long noteId) {
        customerService.deleteCustomerNote(customerId, noteId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{id}/notes")
    public ResponseEntity<Object> createCustomerNote(@PathVariable("id") Long customerId, @Valid @RequestBody CustomerNoteRequest request) {
        return new ResponseEntity<>(customerService.createCustomerNote(customerId, request), HttpStatus.OK);
    }

    @PutMapping("/{id}/notes/{noteId}")
    public ResponseEntity<Object> updateCustomerNote(@PathVariable("id") Long customerId, @PathVariable("noteId") Long noteId,
                                                   @Valid @RequestBody CustomerNoteRequest request) {
        return new ResponseEntity<>(customerService.updateCustomerNote(customerId, noteId, request), HttpStatus.OK);
    }


}
