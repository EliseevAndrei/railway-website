package com.eliseev.app.repository.custom.impl;

import com.eliseev.app.models.Station;
import com.eliseev.app.repository.AbstractDAO;
import com.eliseev.app.repository.custom.StationDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StationDAOImpl extends AbstractDAO<Station>
        implements StationDAO {

    public StationDAOImpl() {
        super(Station.class);
    }

    @Override
    public List<Station> findAll() {
        return super.entityManager.createQuery("select s from Station s order by s.name", Station.class)
                .getResultList();
    }

}
