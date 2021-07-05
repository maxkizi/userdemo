package org.maxkizi.userdemo.services;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxkizi.userdemo.exceptions.UserNotFoundException;
import org.maxkizi.userdemo.model.User;
import org.maxkizi.userdemo.model.UserVacation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        service.findById(1000L);
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
        service.update(1000L, simpleUser());
    }

    @Test
    public void getPageOfUsersAndSorting_ShouldOk() {
        for (int i = 1; i <= 50; i++) {
            service.create(simpleUser(i));
        }
        Page<User> list = service.list(PageRequest.of(0, 10), null);
        Assert.assertEquals(10, list.getSize());

        Page<User> list1 = service.list(PageRequest.of(0, 50), "firstName_25");
        boolean actual = list1.stream().allMatch(u -> u.getFirstName().matches("firstName_25"));
        Assert.assertEquals(true, actual);
    }


    @Test
    public void createUserWithVacations_Should_Ok() {
        User user = service.create(simpleUser());
        User foundUser = service.findById(user.getId());
        Assert.assertEquals(simpleUser().getVacations().size(), foundUser.getVacations().size());
    }


    private User simpleUser() {
        User user = new User();
        user.setFirstName("IVAN");
        user.setLastName("IVANOV");
        user.setUserEmail("ivanov@gmail.com");
        user.setUserInfo("Russia, 20 y.o");

        List<UserVacation> vacations = new ArrayList<>();
        vacations.add(new UserVacation(LocalDate.now(), LocalDate.now(), user));
        vacations.add(new UserVacation(LocalDate.now(), LocalDate.now(), user));

        user.setVacations(vacations);
        return user;
    }


    private User simpleUser(int i) {
        User user = new User();
        user.setFirstName("firstName_" + i);
        user.setLastName("lastName_" + i);
        user.setUserEmail("user@gmail.com");
        user.setUserInfo("Some Country, 20 y.o");
//        user.setVacations(new ArrayList<>());
        return user;
    }
}