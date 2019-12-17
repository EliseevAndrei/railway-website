package com.eliseev.app.repository.custom;

import com.eliseev.app.models.User;
import com.eliseev.app.repository.IDAO;

public interface UserDAO extends IDAO<User> {

    User findByUsername(String s);
}
