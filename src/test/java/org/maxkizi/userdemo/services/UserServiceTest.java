package org.maxkizi.userdemo.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxkizi.userdemo.domain.User;
import org.maxkizi.userdemo.generated.dto.FullUserDto;
import org.maxkizi.userdemo.generated.dto.PartUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService service;


    @Test
    public void getUserByIdTest() {
        FullUserDto userDto = service.create(simpleUser());
        Long id = userDto.getId();
        FullUserDto foundUser = service.findById(id);

        Assert.assertEquals(userDto.getLastName(), foundUser.getLastName());

        service.delete(id);
    }

    @Test
    public void saveAndDeleteUserTest() {
        int sizeBefore = service.list().size();

        FullUserDto user = simpleUser();

        Long generatedId = service.create(user).getId();
        service.delete(generatedId);

        int sizeAfter = service.list().size();

        Assert.assertEquals(sizeAfter, sizeBefore);
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
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setEMail("user@gmail.com");
        user.setUserInfo("Russia, 20 y.o");
        return user;
    }
}