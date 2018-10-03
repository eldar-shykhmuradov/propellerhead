package com.elworld.propellerhead.repositories;

import com.elworld.propellerhead.db.tables.CustomerNote;
import com.elworld.propellerhead.db.tables.records.CustomerNoteRecord;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.elworld.propellerhead.db.Tables.CUSTOMER_NOTE;

@Repository
public class CustomerNoteRepository extends BaseRepository<CustomerNoteRecord, CustomerNote> {

    CustomerNoteRepository(final DSLContext jooq) {
        super(jooq);
    }

    @Override
    protected CustomerNote table() {
        return CUSTOMER_NOTE;
    }


    public List<CustomerNoteRecord> getCustomerNotes(Long customerId, Integer number, Integer count) {
        return jooq().selectFrom(CUSTOMER_NOTE).where(CUSTOMER_NOTE.CUSTOMER_ID.eq(customerId))
                .offset(number)
                .limit(count)
                .fetch();
    }


    public Optional<CustomerNoteRecord> getCustomerNoteById(Long noteId) {
        return jooq().selectFrom(CUSTOMER_NOTE).where(CUSTOMER_NOTE.ID.eq(noteId)).fetchOptional();
    }
}
