package org.maxkizi.userdemo.services;

import lombok.extern.slf4j.Slf4j;
import org.maxkizi.userdemo.converter.UserConverter;
import org.maxkizi.userdemo.domain.User;
import org.maxkizi.userdemo.exceptions.UserNotFoundException;
import org.maxkizi.userdemo.generated.dto.FullUserDto;
import org.maxkizi.userdemo.generated.dto.PartUserDto;
import org.maxkizi.userdemo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {


    private UserRepository repository;
    private UserConverter converter;

    @Autowired
    public UserServiceImpl(UserRepository repository, UserConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public UserServiceImpl() {
    }

    @Override
    public Page<PartUserDto> list(Pageable pageable, String firstName) {
        Page<User> users = repository.findAllByFirstName(pageable, firstName);
        return users.map(u -> converter.toPartUserDto(u));
    }

    @Override
    public Page<PartUserDto> list(Pageable pageable) {
        Page<User> users = repository.findAll(pageable);
        return users.map(u -> converter.toPartUserDto(u));
    }


    @Transactional
    @Override
    public FullUserDto findById(Long id) {
        User user = repository.findById(id).orElseThrow(UserNotFoundException::new);
        return converter.toFullUserDto(user);
    }

    @Transactional
    @Override
    public FullUserDto create(FullUserDto userDto) {
        return converter.toFullUserDto(repository.save(converter.from(userDto)));
    }

    @Transactional
    @Override
    public FullUserDto update(Long id, FullUserDto userDto) {
        //проверяем, есть ли пользователь с таким id
        repository.findById(id).orElseThrow(UserNotFoundException::new);

        userDto.setId(id);
        User updatedUser = converter.from(userDto);
        return converter.toFullUserDto(repository.save(updatedUser));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.findById(id).orElseThrow(UserNotFoundException::new);
        repository.deleteById(id);
    }
}
