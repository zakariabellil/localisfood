package com.LocalisFood.LocalisFood.dto;


import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.LocalisFood.LocalisFood.Model.UserRole;

@Data
public class UserResponseDto {
    @ApiModelProperty(position = 0)
    private Integer id;
    @ApiModelProperty(position = 1)
    private String username;
    @ApiModelProperty(position = 2)
    private String email;
    @ApiModelProperty(position = 3)
    List<UserRole> appUserRoles;
}