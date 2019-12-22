package com.eliseev.app.dto.mapper;

import com.eliseev.app.dto.CarriageDto;
import com.eliseev.app.models.Carriage;
import com.eliseev.app.models.Train;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class CarriageMapper extends AbstractMapper<Carriage, CarriageDto> {

    private final ModelMapper mapper;


    public CarriageMapper(ModelMapper mapper) {
        super(Carriage.class, CarriageDto.class);
        this.mapper = mapper;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Carriage.class, CarriageDto.class)
                .addMappings(m -> m.skip(CarriageDto::setTrainId))
                .setPostConverter(toDtoConverter());

        mapper.createTypeMap(CarriageDto.class, Carriage.class)
                .addMappings(m -> m.skip(Carriage::setTrain))
                .setPostConverter(toEntityConverter());
    }

    @Override
    void mapSpecificFields(Carriage source, CarriageDto destination) {
        destination.setTrainId(this.getId(source));
    }

    private Long getId(Carriage source) {
        return Objects.isNull(source) || Objects.isNull(source.getId())
                ? null
                : source.getTrain().getId();
    }

    @Override
    void mapSpecificFields(CarriageDto source, Carriage destination) {
        Train train = new Train();
        train.setId(source.getId());
        destination.setTrain(train);
    }
}
