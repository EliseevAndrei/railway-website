package com.eliseev.app.repository.custom.impl;

import com.eliseev.app.models.Train;
import com.eliseev.app.repository.AbstractDAO;
import com.eliseev.app.repository.custom.TrainDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class TrainDAOImpl extends AbstractDAO<Train>
        implements TrainDAO {

    private Logger logger = LoggerFactory.getLogger(TrainDAOImpl.class);

    public TrainDAOImpl() {
        super(Train.class);
    }

}
