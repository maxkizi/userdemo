package org.maxkizi.userdemo.services;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxkizi.userdemo.exceptions.UserNotFoundException;
import org.maxkizi.userdemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IUserServiceTest {

    @Autowired
    IUserService service;

    @Test
    public void createAndFindUserByExistingId_Should_Ok() {
        User createdUser = service.create(simpleUser());
        User foundUser = service.findById(createdUser.getId());
        Assert.assertEquals(createdUser.getId(), foundUser.getId());
    }

    @Test(expected = UserNotFoundException.class)
    public void findUserByUnExistingId_Should_ThrowEx() {
        service.findById(43L);
    }

    @Test
    public void createAndDeleteUser_Should_Ok() {
        User createdUser = service.create(simpleUser());
        service.delete(createdUser.getId());
    }

    @Test(expected = UserNotFoundException.class)
    public void deleteByUnExistingId_Should_ThrowEx() {
        service.delete(68L);
    }

    @Test
    public void createAndUpdateUser_Should_Ok() {
        User createdUser = service.create(simpleUser());
        createdUser.setUserEmail("simpleEmail@gmail.com");
        User updatedUser = service.update(createdUser.getId(), createdUser);
        Assert.assertEquals(createdUser.getId(), updatedUser.getId());
        Assert.assertEquals(createdUser.getUserEmail(), updatedUser.getUserEmail());
    }

    @Test(expected = UserNotFoundException.class)
    public void updateUnExistingUser_Should_ThrowEx() {
        service.update(45L, simpleUser());
    }


    private User simpleUser() {
        User user = new User();
        user.setFirstName("IVAN");
        user.setLastName("IVANOV");
        user.setUserEmail("ivanov@gmail.com");
        user.setUserInfo("Russia, 20 y.o");
        user.setVacations(new ArrayList<>());
        return user;
    }
}