package com.eliseev.app.models;

import java.io.Serializable;

public abstract class AbstractEntity implements Serializable {

    protected long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
