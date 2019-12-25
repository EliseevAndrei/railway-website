package com.eliseev.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TrainDateServiceTest {

    Logger logger = LoggerFactory.getLogger(TrainDateServiceTest.class);

    /*@Autowired
    private TrainDateService trainDateService;

    @Autowired
    private StationService stationService;

    @Autowired
    private TrainService trainService;

    @Autowired
    private TrainRoutePieceService trainRoutePieceService;

    @Autowired
    private StationStopTimeService stationStopTimeService;*/

    /*@Test
    @Transactional
    public void getDates() {
        List<TrainStopTimeDTO> trainDateDTOS = trainDateService.getDates(1);
        assertNotNull(trainDateDTOS);
        logger.info("{}", trainDateDTOS);
    }

    @Test
    @Transactional
    public void createTrainDate() {

        List<TrainRoutePiece> trainRoutePieces = trainRoutePieceService.list(1L);

        StationStopTimeDTO stationStopTimeDTO;
        List<StationStopTimeDTO> stationStopTimeDTOs = new ArrayList<>();
        for (TrainRoutePiece trainRoutePiece : trainRoutePieces) {
            stationStopTimeDTO = new StationStopTimeDTO(trainRoutePiece.getId(), new Date(), new Date());
            stationStopTimeDTOs.add(stationStopTimeDTO);
        }

        TrainDate trainDate =  trainDateService.create(stationStopTimeDTOs, 1L);

        logger.info("{}",trainDate);
        logger.info("{}", trainDate.getStationStopTimes());

    }

    @Test
    @Transactional
    public void getTrainDates() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = simpleDateFormat.parse("2019-10-11");

            List<TrainRouteDTO> trainRouteDTOS = trainDateService.getTrainDates(stationService.get(1L), stationService.get(4L), date);
            logger.info("{}", trainRouteDTOS);


        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
*/

}
