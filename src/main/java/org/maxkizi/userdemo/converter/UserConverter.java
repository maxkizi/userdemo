package org.maxkizi.userdemo.converter;

import lombok.RequiredArgsConstructor;
import org.maxkizi.userdemo.domain.User;
import org.maxkizi.userdemo.generated.dto.FullUserDto;
import org.maxkizi.userdemo.generated.dto.PartUserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;



@Component
@RequiredArgsConstructor
public class UserConverter {

    @Qualifier("standardModelMapper")
    private final ModelMapper modelMapper;

    public PartUserDto toPartUserDto(User user){
        return  modelMapper.map(user, PartUserDto.class);
    }

    public FullUserDto toFullUserDto(User user){
        return  modelMapper.map(user, FullUserDto.class);
    }

    public User from(FullUserDto fullUserDto){
        User user = modelMapper.map(fullUserDto, User.class);
        return user;
    }
}
