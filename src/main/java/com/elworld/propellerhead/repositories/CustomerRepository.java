package com.elworld.propellerhead.repositories;

import com.elworld.propellerhead.db.tables.Customer;
import com.elworld.propellerhead.db.tables.records.CustomerRecord;
import lombok.Data;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.stream.Collectors;

import static com.elworld.propellerhead.db.Tables.CUSTOMER;

@Repository
public class CustomerRepository extends BaseRepository<CustomerRecord, Customer> {

    CustomerRepository(final DSLContext jooq) {
        super(jooq);
    }

    @Override
    protected Customer table() {
        return CUSTOMER;
    }

}
