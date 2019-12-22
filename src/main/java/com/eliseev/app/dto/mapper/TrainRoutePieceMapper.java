package com.eliseev.app.dto.mapper;

import com.eliseev.app.dto.TrainRoutePieceDto;
import com.eliseev.app.models.TrainRoutePiece;
import com.eliseev.app.models.Train;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class TrainRoutePieceMapper extends AbstractMapper<TrainRoutePiece, TrainRoutePieceDto> {

    private final ModelMapper mapper;


    public TrainRoutePieceMapper(ModelMapper mapper) {
        super(TrainRoutePiece.class, TrainRoutePieceDto.class);
        this.mapper = mapper;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(TrainRoutePiece.class, TrainRoutePieceDto.class)
                .addMappings(m -> m.skip(TrainRoutePieceDto::setTrainId))
                .setPostConverter(toDtoConverter());

        mapper.createTypeMap(TrainRoutePieceDto.class, TrainRoutePiece.class)
                .addMappings(m -> m.skip(TrainRoutePiece::setTrain))
                .setPostConverter(toEntityConverter());
    }

    @Override
    void mapSpecificFields(TrainRoutePiece source, TrainRoutePieceDto destination) {
        destination.setTrainId(this.getId(source));
    }

    private Long getId(TrainRoutePiece source) {
        return Objects.isNull(source) || Objects.isNull(source.getId())
                ? null
                : source.getTrain().getId();
    }

    @Override
    void mapSpecificFields(TrainRoutePieceDto source, TrainRoutePiece destination) {
        Train train = new Train();
        train.setId(source.getId());
        destination.setTrain(train);
    }
}