package com.eliseev.app.dto.mapper;

import com.eliseev.app.dto.RoleDto;
import com.eliseev.app.models.Role;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper extends AbstractMapper<Role, RoleDto> {

    private final ModelMapper mapper;

    @Autowired
    public RoleMapper(ModelMapper mapper) {
        super(Role.class, RoleDto.class);
        this.mapper = mapper;
    }

    /*@PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Role.class, RoleDto.class)
                .addMappings(m -> m.skip(RoleDto::setUsersId))
                .setPostConverter(toDtoConverter());

        mapper.createTypeMap(RoleDto.class, Role.class)
                .addMappings(m -> m.skip(Role::setUsers))
                .setPostConverter(toEntityConverter());
    }

    @Override
    void mapSpecificFields(Role source, RoleDto destination) {
        List<Long> usersId = new ArrayList<>();
        source.getUsers().forEach(e -> {
            if (e.getId() != null) {
                usersId.add(e.getId());
            }
        });
        destination.setUsersId(usersId);
    }

    @Override
    void mapSpecificFields(RoleDto source, Role destination) {
        List<User> users = new ArrayList<>();
        source.getUsersId().forEach(e -> {
            if (e != null) {
                User user = new User();
                user.setId(e);
                users.add(user);
            }
        });
        destination.setUsers(users);
    }*/
}
