package com.eliseev.app.repository.custom;

import com.eliseev.app.models.Role;
import com.eliseev.app.models.UserRoleEnum;
import com.eliseev.app.repository.IDAO;

public interface RoleDAO extends IDAO<Role> {
    Role findByName(UserRoleEnum name);
}
