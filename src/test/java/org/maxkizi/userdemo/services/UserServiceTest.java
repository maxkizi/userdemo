package org.maxkizi.userdemo.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxkizi.userdemo.domain.User;
import org.maxkizi.userdemo.exceptions.UserNotFoundException;
import org.maxkizi.userdemo.generated.dto.FullUserDto;
import org.maxkizi.userdemo.generated.dto.PartUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserServiceImpl service;


    //    Заполнение рандомными данныим
    @Test
    public void simpleTest() {

       /* for (int i = 0; i <100; i++){
            FullUserDto user = simpleUser(i);
            service.create(user);
        }*/
    }

    @Test
    public void getUserByIdTest() {
        FullUserDto userDto = service.create(simpleUser());
        Long id = userDto.getId();
        FullUserDto foundUser = service.findById(id);

        Assert.assertEquals(userDto.getLastName(), foundUser.getLastName());

        service.delete(id);
    }

    @Test(expected = UserNotFoundException.class)
    public void getUserById_ShouldThrows_UserNotFoundException() {
        service.findById(10000L);
    }

    @Test
    public void saveAndDeleteUserTest() {

        FullUserDto user = simpleUser();
        FullUserDto createdUser = service.create(user);
        FullUserDto foundUser = service.findById(createdUser.getId());

        Assert.assertEquals(user.getUserInfo(), foundUser.getUserInfo());

        service.delete(createdUser.getId());
    }

    @Test
    public void updateUserTest() {
        FullUserDto userDto = service.create(simpleUser());
        Long id = userDto.getId();
        userDto.setLastName("UpdatedName");
        service.update(id, userDto);
        FullUserDto updatedUser = service.findById(id);
        Assert.assertEquals(userDto.getLastName(), updatedUser.getLastName());

        service.delete(id);

    }


    private FullUserDto simpleUser() {
        FullUserDto user = new FullUserDto();
        user.setFirstName("IVAN");
        user.setLastName("IVANOV");
        user.setUserEmail("ivanov@gmail.com");
        user.setUserInfo("Russia, 20 y.o");
        return user;
    }

    private FullUserDto simpleUser(int i) {
        FullUserDto user = new FullUserDto();
        user.setFirstName("firstName_" + i);
        user.setLastName("lastName_" + i);
        return user;
    }
}