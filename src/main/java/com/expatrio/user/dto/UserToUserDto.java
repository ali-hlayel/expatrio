package com.expatrio.user.dto;

import com.expatrio.user.domain.User;
import org.springframework.beans.BeanUtils;

public class UserToUserDto {

    public static UserDto convertUserToUserDto(User user) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        userDto.setRole(user.getRole().name());
        return userDto;
    }
}
