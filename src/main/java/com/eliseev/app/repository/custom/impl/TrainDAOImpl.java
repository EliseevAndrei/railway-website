package com.eliseev.app.repository.custom.impl;

import com.eliseev.app.models.Station;
import com.eliseev.app.models.Train;
import com.eliseev.app.repository.AbstractDAO;
import com.eliseev.app.repository.custom.TrainDAO;
import com.eliseev.app.services.dto.TrainDateDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class TrainDAOImpl extends AbstractDAO<Train>
        implements TrainDAO {

    private Logger logger = LoggerFactory.getLogger(TrainDAOImpl.class);

    public TrainDAOImpl() {
        super(Train.class);
    }

}
