package com.elworld.propellerhead.repositories;

import org.jooq.DSLContext;
import org.jooq.UpdatableRecord;
import org.jooq.impl.TableImpl;

public abstract class BaseRepository<R extends UpdatableRecord, T extends TableImpl<R>> {

    private final DSLContext jooq;

    BaseRepository(DSLContext jooq) {this.jooq = jooq;}

    public DSLContext jooq() {
        return jooq;
    }

    public R newRecord() {
        return jooq().newRecord(table());
    }

    public void store(R record) {
        record.store();
    }

    public void delete(R record) {
        record.delete();
    }


    protected abstract T table();


}
