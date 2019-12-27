package com.eliseev.app.dto;

import com.eliseev.app.models.UserRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RoleDto extends AbstractDto {

    private UserRoleEnum name;
    /*private List<Long> usersId;*/

}
