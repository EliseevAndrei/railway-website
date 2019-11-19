package com.eliseev.app.repository.custom;

import com.eliseev.app.models.User;
import com.eliseev.app.repository.AbstractDAO;
import com.eliseev.app.repository.IDAO;

public interface UserDAO extends IDAO<User> {

    User findByLoginAndPass(String login, String pass);

}
