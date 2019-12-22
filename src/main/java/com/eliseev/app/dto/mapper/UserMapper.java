package com.eliseev.app.dto.mapper;

import com.eliseev.app.dto.UserDto;
import com.eliseev.app.models.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends AbstractMapper<User, UserDto> {

    private final ModelMapper mapper;

    public UserMapper(ModelMapper mapper) {
        super(User.class, UserDto.class);
        this.mapper = mapper;
    }

}
