package com.elworld.propellerhead.repositories;

import com.elworld.propellerhead.db.enums.CustomerStatus;
import com.elworld.propellerhead.db.tables.Customer;
import com.elworld.propellerhead.db.tables.records.CustomerRecord;
import com.elworld.propellerhead.models.enums.SortColumn;
import com.elworld.propellerhead.models.enums.SortDirection;
import org.apache.commons.lang3.StringUtils;
import org.jooq.*;
import org.jooq.impl.CustomRecord;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

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

    public List<CustomerRecord> getCustomers(CustomerStatus status, String text, SortColumn sortColumn, SortDirection sortDirection, Integer number, Integer count) {
        final SortField sortField = getSortFieldForCustomers(sortColumn, sortDirection);
        Condition customerCondition = DSL.trueCondition();
        if (status != null) {
            customerCondition = customerCondition.and(CUSTOMER.STATUS.eq(status));
        }
        if (StringUtils.isNotBlank(text)) {
            customerCondition = customerCondition.and(CUSTOMER.NAME.likeIgnoreCase(wrapSearchText(text)));
        }

        return jooq().selectFrom(CUSTOMER)
                .where(customerCondition)
                .orderBy(sortField)
                .offset(number)
                .limit(count)
                .fetch();
    }

    public Optional<CustomerRecord> getCustomerById(Long customerId) {
        return jooq().selectFrom(CUSTOMER).where(CUSTOMER.ID.eq(customerId)).fetchOptional();
    }

    private SortField getSortFieldForCustomers(SortColumn sortColumn, SortDirection direction) {
        final TableField tableField;
        switch(sortColumn){
            case DATE:
                tableField = CUSTOMER.CREATE_TIMESTAMP;
                break;
            default:
                throw new IllegalStateException("Invalid sort column:" + sortColumn.name());
        }

        if (direction.equals(SortDirection.ASC)) {
            return tableField.asc();
        } else {
            return tableField.desc();
        }
    }

    private String wrapSearchText(String text) {
        return "%" + text + "%";
    }

}
