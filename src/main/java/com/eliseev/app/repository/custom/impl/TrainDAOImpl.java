package com.eliseev.app.repository.custom.impl;

import com.eliseev.app.models.Train;
import com.eliseev.app.repository.AbstractDAO;
import com.eliseev.app.repository.custom.TrainDAO;
import org.springframework.stereotype.Repository;

@Repository
public class TrainDAOImpl extends AbstractDAO<Train>
        implements TrainDAO {

    public TrainDAOImpl() {
        super(Train.class);
    }

}
