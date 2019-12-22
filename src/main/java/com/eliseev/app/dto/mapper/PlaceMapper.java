package com.eliseev.app.dto.mapper;

import com.eliseev.app.dto.PlaceDto;
import com.eliseev.app.models.Carriage;
import com.eliseev.app.models.Place;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;



@Component
public class PlaceMapper extends AbstractMapper<Place, PlaceDto> {

    private final ModelMapper mapper;


    public PlaceMapper(ModelMapper mapper) {
        super(Place.class, PlaceDto.class);
        this.mapper = mapper;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Place.class, PlaceDto.class)
                .addMappings(m -> m.skip(PlaceDto::setCarriageId))
                .setPostConverter(toDtoConverter());

        mapper.createTypeMap(PlaceDto.class, Place.class)
                .addMappings(m -> m.skip(Place::setCarriage))
                .setPostConverter(toEntityConverter());
    }

    @Override
    void mapSpecificFields(Place source, PlaceDto destination) {
        destination.setCarriageId(this.getId(source));
    }

    private Long getId(Place source) {
        return Objects.isNull(source) || Objects.isNull(source.getCarriage())
                ? null
                : source.getCarriage().getId();
    }

    @Override
    void mapSpecificFields(PlaceDto source, Place destination) {
        Carriage carriage = new Carriage();
        carriage.setId(source.getCarriageId());
        destination.setCarriage(carriage);
    }
}
