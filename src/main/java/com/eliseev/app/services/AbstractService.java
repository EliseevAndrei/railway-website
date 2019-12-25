package com.eliseev.app.services;

import com.eliseev.app.dto.AbstractDto;
import com.eliseev.app.dto.mapper.IMapper;
import com.eliseev.app.models.AbstractEntity;
import com.eliseev.app.repository.IDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractService<E extends AbstractEntity, D extends AbstractDto,
        R extends IDAO<E>> implements IService<D> {

    Logger logger = LoggerFactory.getLogger(AbstractService.class);

    protected R dao;
    private IMapper<E, D> mapper;

    public AbstractService(R dao, IMapper<E, D> mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @Transactional
    @Override
    public List<D> list() {
        List<E> entities = dao.findAll( "");
        return mapper.toDto(entities, new ArrayList<>());

    }

    @Transactional
    @Override
    public D create(D dto) {
        return mapper.toDto(dao.save(mapper.toEntity(dto)));
    }

    @Transactional
    @Override
    public D get(long id) {
        D dto = mapper.toDto(dao.findOne(id, ""));
        logger.info("found entity {} with id {}", dto, id);
        return dto;
    }

    @Transactional
    @Override
    public D update(long id, D dto) {
        dto.setId(id);
        return mapper.toDto(dao.save(mapper.toEntity(dto)));
    }

    @Transactional
    @Override
    public void delete(long id) {
         dao.delete(id);
    }


}
