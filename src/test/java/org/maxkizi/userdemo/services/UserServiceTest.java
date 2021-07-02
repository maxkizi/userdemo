package org.maxkizi.userdemo.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxkizi.userdemo.converter.UserConverter;
import org.maxkizi.userdemo.exceptions.UserNotFoundException;
import org.maxkizi.userdemo.generated.dto.UserDto;
import org.maxkizi.userdemo.generated.dto.UserVacationDto;
import org.maxkizi.userdemo.model.User;
import org.maxkizi.userdemo.model.UserVacation;
import org.maxkizi.userdemo.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest

public class UserServiceTest {
    @Autowired
    private UserServiceImpl service;
    @Autowired
    private UserConverter converter;


    //    Заполнение рандомными данныим
    @Test
    public void simpleTest() {

        for (int i = 1; i <= 50; i++) {
            User user = simpleUser(i);
            service.create(user);
        }
    }


    private User simpleUser() {
        User user = new User();
        user.setFirstName("IVAN");
        user.setLastName("IVANOV");
        user.setUserEmail("ivanov@gmail.com");
        user.setUserInfo("Russia, 20 y.o");
        return user;
    }

    private User simpleUser(int i) {
        User user = new User();
        user.setFirstName("firstName_" + i);
        user.setLastName("lastName_" + i);

        return user;
    }
}