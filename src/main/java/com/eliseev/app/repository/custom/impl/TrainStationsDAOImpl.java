package com.eliseev.app.repository.custom.impl;

import com.eliseev.app.models.TrainStation;
import com.eliseev.app.repository.AbstractDAO;
import com.eliseev.app.repository.custom.TrainStationsDAO;
import org.springframework.stereotype.Repository;

@Repository
public class TrainStationsDAOImpl extends AbstractDAO<TrainStation>
        implements TrainStationsDAO {

    public TrainStationsDAOImpl() {
        super(TrainStation.class);
    }

}
