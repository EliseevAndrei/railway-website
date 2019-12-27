package com.eliseev.app.services;

import com.eliseev.app.dto.UserDto;
import com.eliseev.app.dto.mapper.UserMapper;
import com.eliseev.app.models.User;
import com.eliseev.app.repository.custom.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService extends AbstractService<User, UserDto, UserDAO>
            implements UserDetailsService {

    private UserMapper userMapper;

    @Autowired
    public UserService(UserDAO dao, UserMapper userMapper) {
        super(dao, userMapper);
        this.userMapper = userMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = dao.findByUsername(s);
        if (user != null) {
            return user;
        }
        throw new UsernameNotFoundException(
                "User '" + s + "' not found");
    }

    @Override
    public UserDto create(UserDto dto) {
        User user = userMapper.toEntity(dto);
        if (dao.findByUsername(user.getLogin()) != null) {
            return null;
        }
        UserDto userDto = userMapper.toDto(dao.save(user));


        return userDto;
    }
}
