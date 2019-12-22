package com.eliseev.app.dto.mapper;

import com.eliseev.app.dto.TrainDateDto;
import com.eliseev.app.models.TrainDate;
import com.eliseev.app.models.Train;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class TrainDateMapper extends AbstractMapper<TrainDate, TrainDateDto> {

    private final ModelMapper mapper;


    public TrainDateMapper(ModelMapper mapper) {
        super(TrainDate.class, TrainDateDto.class);
        this.mapper = mapper;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(TrainDate.class, TrainDateDto.class)
                .addMappings(m -> m.skip(TrainDateDto::setTrainId))
                .setPostConverter(toDtoConverter());

        mapper.createTypeMap(TrainDateDto.class, TrainDate.class)
                .addMappings(m -> m.skip(TrainDate::setTrain))
                .setPostConverter(toEntityConverter());
    }

    @Override
    void mapSpecificFields(TrainDate source, TrainDateDto destination) {
        destination.setTrainId(this.getId(source));
    }

    private Long getId(TrainDate source) {
        return Objects.isNull(source) || Objects.isNull(source.getId())
                ? null
                : source.getTrain().getId();
    }

    @Override
    void mapSpecificFields(TrainDateDto source, TrainDate destination) {
        Train train = new Train();
        train.setId(source.getId());
        destination.setTrain(train);
    }
}
