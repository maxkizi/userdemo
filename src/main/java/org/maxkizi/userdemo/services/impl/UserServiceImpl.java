package org.maxkizi.userdemo.services.impl;

import com.querydsl.core.BooleanBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.maxkizi.userdemo.converter.UserConverter;
import org.maxkizi.userdemo.exceptions.AddVacationException;
import org.maxkizi.userdemo.exceptions.UserNotFoundException;
import org.maxkizi.userdemo.generated.dto.UserDto;
import org.maxkizi.userdemo.generated.dto.UserShortDto;
import org.maxkizi.userdemo.generated.dto.UserVacationDto;
import org.maxkizi.userdemo.model.QUser;
import org.maxkizi.userdemo.model.User;
import org.maxkizi.userdemo.repositories.UserRepository;
import org.maxkizi.userdemo.services.AbstractBaseService;
import org.maxkizi.userdemo.services.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@AllArgsConstructor
@Getter
@Slf4j
public class UserServiceImpl extends AbstractBaseService<User, Long, QUser, UserRepository> implements IUserService {

    private final UserRepository repository;
    private final UserConverter converter;

    @Override
    public Page<UserShortDto> list(Pageable pageable, String search) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(QUser.user.deleted.isFalse());
        if (StringUtils.hasText(search)) {
            booleanBuilder.and(QUser.user.name.containsIgnoreCase(search))
                    .or(QUser.user.firstName.containsIgnoreCase(search));
        }
        return findAll(booleanBuilder, pageable).map(converter::toShortDto);
    }

    @Override
    public UserDto findById(Long id) {
        return converter.toDto(get(id).orElseThrow(UserNotFoundException::new));
    }

    @Override
    public UserDto create(UserDto userDto) {
        return converter.toDto(save(converter.from(userDto)));
    }

    @Override
    public UserDto update(Long id, UserDto userDto) {
        User oldUser = get(id).orElseThrow(UserNotFoundException::new);
        userDto.setId(id);
        User user = converter.from(userDto);
        user.setCreatedAt(oldUser.getCreatedAt());
        return converter.toDto(save(user));
    }

    @Override
    public void delete(Long id) {
        User user = get(id).orElseThrow(UserNotFoundException::new);
        user.setDeleted(true);
        save(user);
    }

    @Override
    public UserDto addVacation(UserVacationDto vacationDto, Long userId) {
        try {
            UserDto userDto = findById(userId);
            userDto.getVacations().add(vacationDto);
           return update(userId, userDto);
        } catch (UserNotFoundException e) {
            log.error(e.getMessage());
            throw new AddVacationException();
        }
    }

    @Override
    public UserDto deleteVacation(Long vacationId, Long userId) {
        try {
            UserDto userDto = findById(userId);
            userDto.getVacations().remove(vacationId);
            return update(userId, userDto);
        } catch (UserNotFoundException e) {
            log.error(e.getMessage());
            throw new AddVacationException();
        }
    }
}
