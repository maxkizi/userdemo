package org.maxkizi.userdemo.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxkizi.userdemo.converter.UserConverter;
import org.maxkizi.userdemo.exceptions.UserNotFoundException;
import org.maxkizi.userdemo.generated.dto.UserDto;
import org.maxkizi.userdemo.model.User;
import org.maxkizi.userdemo.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

     /*   for (int i = 1; i <= 100; i++) {
            FullUserDto user = simpleUser(i);
            service.create(user);
        }*/
    }

    @Test
    public void getUserByIdTest() {
        Long id = 100L;
        UserDto user = service.findById(id);
        Assert.assertEquals(id, user.getId());

    }

    @Test(expected = UserNotFoundException.class)
    public void getUserById_ShouldThrows_UserNotFoundException() {
        service.findById(10000L);
    }

    @Test
    public void saveUserTest() {
        Long id = service.create(simpleUser()).getId();
        UserDto foundUser = service.findById(id);

        Assert.assertEquals(foundUser.getLastName(), foundUser.getLastName());
    }



    private UserDto simpleUser() {
        UserDto user = new UserDto();
        user.setFirstName("IVAN");
        user.setLastName("IVANOV");
        user.setUserEmail("ivanov@gmail.com");
        user.setUserInfo("Russia, 20 y.o");
        return user;
    }

    private UserDto simpleUser(int i) {
        UserDto user = new UserDto();
        user.setFirstName("firstName_" + i);
        user.setLastName("lastName_" + i);
        return user;
    }
}