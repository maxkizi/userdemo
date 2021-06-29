package org.maxkizi.userdemo.services;

import org.maxkizi.userdemo.generated.dto.UserDto;
import org.maxkizi.userdemo.generated.dto.UserShortDto;
import org.maxkizi.userdemo.generated.dto.UserVacationDto;
import org.maxkizi.userdemo.model.User;
import org.maxkizi.userdemo.model.UserVacation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public interface IUserService {
    Page<UserShortDto> list(Pageable pageable, String search);

    UserDto findById(Long id);

    UserDto create(UserDto userDto);

    UserDto update(Long id, UserDto userDto);

    void delete(Long id);

    @Transactional
    UserDto addVacation(UserVacationDto vacation, Long userId);

    @Transactional
    UserDto deleteVacation(Long vacationId, Long userId);

}
