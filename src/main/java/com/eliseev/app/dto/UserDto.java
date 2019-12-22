package com.eliseev.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserDto extends AbstractDto {

    @NotBlank(message = "Surname is required")
    private String surname;
    @NotBlank(message = "Name is required")
    private String name;
    @Email(message = "Email must be formatted like sometext@mail.ru")
    private String email;
    private String login;
    private String pass;
    private List<RoleDto> roles;

}
