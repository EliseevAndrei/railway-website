package com.eliseev.app.repository.custom.impl;

import com.eliseev.app.models.Carriage;
import com.eliseev.app.models.Train;
import com.eliseev.app.repository.AbstractDAO;
import com.eliseev.app.repository.custom.TrainDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TrainDAOImpl extends AbstractDAO<Train>
        implements TrainDAO {

    private Logger logger = LoggerFactory.getLogger(TrainDAOImpl.class);

    public TrainDAOImpl() {
        super(Train.class);
    }

    public Map<String, Integer> getFreePlacesAmountForTrainRoute(long trainId,
                                                                 long trainDateId,
                                                                 int depRoutePieceSerialNumber,
                                                                 int arrRoutePieceSerialNumber) {

        @SuppressWarnings("unchecked")
        List<Object[]> objects = super.entityManager.createNativeQuery(
                "select c.type, count(1) from carriage c\n" +
                        "left join place on place.carriage_id = c.id\n" +
                        "where train_id = :trainId and place.id  not in (\n" +
                        "           select place_id as placeId from(" +
                        "               select * from ticket t\n" +
                        "               where t.train_date_id = :trainDateId" +
                        "           ) as ta" +
                        "           left join train_route_piece trp1 on trp1.id = ta.dep_train_route_piece_id\n" +
                        "           left join train_route_piece trp2 on trp2.id = ta.arr_train_route_piece_id\n" +
                        "       where   (trp1.serial_number between :depRoutePieceSerialNumber and :arrRoutePieceSerialNumber) \n" +
                        "           or (trp2.serial_number between :depRoutePieceSerialNumber and :arrRoutePieceSerialNumber)\n" +
                        ")\n" +
                        "group by c.type;")
                .setParameter("trainId", trainId)
                .setParameter("trainDateId", trainDateId)
                .setParameter("depRoutePieceSerialNumber", depRoutePieceSerialNumber)
                .setParameter("arrRoutePieceSerialNumber", arrRoutePieceSerialNumber)
                .getResultList();

        Map<String, Integer> freePlaces = new LinkedHashMap<>();


        for (Object[] object : objects) {
            logger.info("{} - {}", object[0], object[1]);
            freePlaces.put((String) object[0], ((BigInteger) object[1]).intValue());
        }

        return freePlaces;
    }

    @Override
    public List<Carriage> getCarriages(long trainId, long trainDateId,
                                       int depRoutePieceSerialNumber,
                                       int arrRoutePieceSerialNumber) {
        List<Carriage> carriages = super.entityManager.createQuery(
                "select distinct carriage from Carriage carriage\n" +
                        "join fetch carriage.places place\n" +
                        "where carriage.train.id = :trainId and place.id not in (" +
                        "   select t.place.id from Ticket t" +
                        "       left join TrainRoutePiece trp1 on trp1.id = t.depTrainRoutePiece.id\n" +
                        "       left join TrainRoutePiece trp2 on trp2.id = t.arrTrainRoutePiece.id\n" +
                        "   where t.trainDate.id = :trainDateId and (trp1.serialNumber between :depRoutePieceSerialNumber and :arrRoutePieceSerialNumber) \n" +
                        "or (trp2.serialNumber between :depRoutePieceSerialNumber and :arrRoutePieceSerialNumber)\n" +
                        ")", Carriage.class)
                .setParameter("trainDateId", trainDateId)
                .setParameter("trainId", trainId)
                .setParameter("depRoutePieceSerialNumber", depRoutePieceSerialNumber)
                .setParameter("arrRoutePieceSerialNumber", arrRoutePieceSerialNumber)
                .getResultList();
        logger.info("{}", carriages.get(0).getPlaces());
        return carriages;
    }

    @Override
    public List<Carriage> getCarriages(long trainId) {
        return super.entityManager.createQuery(
                "select distinct carriage from Carriage carriage\n" +
                        "left join fetch carriage.places place\n" +
                        "where carriage.train.id = :trainId", Carriage.class)
                .setParameter("trainId", trainId)
                .getResultList();
    }


}
