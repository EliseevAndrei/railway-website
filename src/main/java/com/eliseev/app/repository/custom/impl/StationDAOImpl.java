package com.eliseev.app.repository.custom.impl;

import com.eliseev.app.models.Station;
import com.eliseev.app.repository.AbstractDAO;
import com.eliseev.app.repository.custom.StationDAO;
import org.springframework.stereotype.Repository;

@Repository
public class StationDAOImpl extends AbstractDAO<Station>
        implements StationDAO {

    public StationDAOImpl() {
        super(Station.class);
    }
}
