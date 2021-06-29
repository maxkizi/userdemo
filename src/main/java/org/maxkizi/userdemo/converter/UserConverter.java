package org.maxkizi.userdemo.converter;

import lombok.RequiredArgsConstructor;
import org.maxkizi.userdemo.generated.dto.UserDto;
import org.maxkizi.userdemo.generated.dto.UserShortDto;
import org.maxkizi.userdemo.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserConverter {

    @Qualifier("standardModelMapper")
    private final ModelMapper modelMapper;

    public UserShortDto toShortDto(User user) {
        return modelMapper.map(user, UserShortDto.class);
    }

    public UserDto toDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public User from(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

}
