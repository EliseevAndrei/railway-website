package com.eliseev.app.models;

public abstract class AbstractEntity {

    protected long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
