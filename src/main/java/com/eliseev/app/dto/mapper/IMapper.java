package com.eliseev.app.dto.mapper;

import com.eliseev.app.dto.AbstractDto;
import com.eliseev.app.models.AbstractEntity;

public interface IMapper<E extends AbstractEntity, D extends AbstractDto> {

    E toEntity(D dto);

    D toDto(E entity);
}
