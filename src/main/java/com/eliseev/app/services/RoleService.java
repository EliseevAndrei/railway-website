package com.eliseev.app.services;

import com.eliseev.app.models.Role;
import com.eliseev.app.models.UserRoleEnum;
import com.eliseev.app.repository.custom.RoleDAO;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends AbstractService<Role, RoleDAO> {

    public RoleService(RoleDAO dao) {
        super(dao);
    }

    public Role findByName(UserRoleEnum name) {
        return super.dao.findByName(name);
    }

}
