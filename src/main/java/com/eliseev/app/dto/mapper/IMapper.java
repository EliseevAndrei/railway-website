package com.eliseev.app.dto.mapper;

import com.eliseev.app.dto.AbstractDto;
import com.eliseev.app.models.AbstractEntity;

import java.util.Collection;

public interface IMapper<E extends AbstractEntity, D extends AbstractDto> {

    E toEntity(D dto);

    D toDto(E entity);

    <S extends Collection<D>, V extends Collection<E>> V toEntity(S source, V destination);

    <S extends Collection<E>, V extends Collection<D>> V toDto(S source, V destination);

}
