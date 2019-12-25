package com.eliseev.app.dto.mapper;

import com.eliseev.app.dto.AbstractDto;
import com.eliseev.app.models.AbstractEntity;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Objects;

public abstract class AbstractMapper<E extends AbstractEntity, D extends AbstractDto>
        implements IMapper<E, D> {

    @Autowired
    ModelMapper mapper;

    private Class<E> entityClass;
    private Class<D> dtoClass;

    public AbstractMapper(Class<E> entityClass, Class<D> dtoClass) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

    @Override
    public E toEntity(D dto) {
        return Objects.isNull(dto)
                ? null
                : mapper.map(dto, entityClass);
    }

    @Override
    public D toDto(E entity) {
        return Objects.isNull(entity)
                ? null
                : mapper.map(entity, dtoClass);
    }


    @Override
    public <S extends Collection<D>, V extends Collection<E>> V toEntity(S source, V destination) {
        source.forEach(e -> {
            destination.add(toEntity(e));
        });
        return destination;
    }

    @Override
    public <S extends Collection<E>, V extends Collection<D>> V toDto(S source, V destination) {
        source.forEach(e -> {
            destination.add(toDto(e));
        });
        return destination;
    }

    Converter<E, D> toDtoConverter() {
        return context -> {
            E source = context.getSource();
            D destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    Converter<D, E> toEntityConverter() {
        return context -> {
            D source = context.getSource();
            E destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }


    void mapSpecificFields(E source, D destination) { }

    void mapSpecificFields(D source, E destination){ }


}
