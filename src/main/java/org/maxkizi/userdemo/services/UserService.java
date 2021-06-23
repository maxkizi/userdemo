package org.maxkizi.userdemo.services;

import org.maxkizi.userdemo.generated.dto.FullUserDto;
import org.maxkizi.userdemo.generated.dto.PartUserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
   List<PartUserDto> list();

    FullUserDto findById(Long id);

    FullUserDto create (FullUserDto userDto);

    FullUserDto update(Long id, FullUserDto userDto);

    void delete(Long id);

}
