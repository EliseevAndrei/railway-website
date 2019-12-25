package com.eliseev.app.dto.mapper;

import com.eliseev.app.dto.StationStopTimeDto;
import com.eliseev.app.models.StationStopTime;
import com.eliseev.app.models.TrainDate;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;


@Component
public class StationStopTimeMapper extends AbstractMapper<StationStopTime, StationStopTimeDto> {

    private final ModelMapper mapper;

    public StationStopTimeMapper(ModelMapper mapper) {
        super(StationStopTime.class, StationStopTimeDto.class);
        this.mapper = mapper;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(StationStopTime.class, StationStopTimeDto.class)
                .addMappings(m -> {
                    m.skip(StationStopTimeDto::setTrainDateId);
                })
                .setPostConverter(toDtoConverter());

        mapper.createTypeMap(StationStopTimeDto.class, StationStopTime.class)
                .addMappings(m -> {
                    m.skip(StationStopTime::setTrainDate);
                })
                .setPostConverter(toEntityConverter());
    }

    @Override
    void mapSpecificFields(StationStopTime source, StationStopTimeDto destination) {
        destination.setTrainDateId(this.getTrainDateId(source));
    }

    private Long getTrainDateId(StationStopTime source) {
        return Objects.isNull(source) || Objects.isNull(source.getTrainDate())
                ? null
                : source.getTrainDate().getId();
    }

    private Long getTraRouteId(StationStopTime source) {
        return Objects.isNull(source) || Objects.isNull(source.getTrainRoutePiece())
                ? null
                : source.getTrainRoutePiece().getId();
    }

    @Override
    void mapSpecificFields(StationStopTimeDto source, StationStopTime destination) {

        TrainDate trainDate = new TrainDate();
        trainDate.setId(source.getTrainDateId());

        destination.setTrainDate(trainDate);

    }
}