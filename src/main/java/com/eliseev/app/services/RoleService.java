package com.eliseev.app.services;

import com.eliseev.app.dto.RoleDto;
import com.eliseev.app.dto.mapper.RoleMapper;
import com.eliseev.app.models.Role;
import com.eliseev.app.models.UserRoleEnum;
import com.eliseev.app.repository.custom.RoleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends AbstractService<Role, RoleDto, RoleDAO> {

    private RoleMapper roleMapper;

    @Autowired
    public RoleService(RoleDAO dao, RoleMapper roleMapper) {
        super(dao, roleMapper);
        this.roleMapper = roleMapper;
    }

    public RoleDto findByName(UserRoleEnum name) {
        return roleMapper.toDto(super.dao.findByName(name));
    }

}
