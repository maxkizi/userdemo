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
        User createdUser = service.create(simpleUser(1));
        User foundUser = service.findById(createdUser.getId());
        Assert.assertEquals(createdUser.getId(), foundUser.getId());
    }

    @Test(expected = UserNotFoundException.class)
    public void findUserByUnExistingId_Should_ThrowEx() {
        service.findById(1000L);
    }

    @Test
    public void createAndDeleteUser_Should_Ok() {
        User createdUser = service.create(simpleUser(1));
        service.delete(createdUser.getId());
    }

    //    удаление удаленного пользователя
    @Test(expected = UserNotFoundException.class)
    public void deleteByUnExistingId_Should_ThrowEx() {
        User createdUser = service.create(simpleUser(1));
        service.delete(createdUser.getId());
        service.delete(createdUser.getId());
    }

    @Test
    public void createAndUpdateUser_Should_Ok() {
        User createdUser = service.create(simpleUser(1));
        createdUser.setUserEmail("simpleEmail@gmail.com");
        User updatedUser = service.update(createdUser.getId(), createdUser);
        Assert.assertEquals(createdUser.getId(), updatedUser.getId());
        Assert.assertEquals(createdUser.getUserEmail(), updatedUser.getUserEmail());
    }

    @Test(expected = UserNotFoundException.class)
    public void updateUnExistingUser_Should_ThrowEx() {
        service.update(1000L, simpleUser(1));
    }


    @Test
    public void createUserWithVacations_Should_Ok() {
        User user = service.create(userWithVacations());
        User foundUser = service.findById(user.getId());
        Assert.assertEquals(userWithVacations().getVacations().size(), foundUser.getVacations().size());
    }

    @Test
    public void updateUserWithVacations_Should_Ok() {
        User createdUser = service.create(userWithVacations());
        User userToUpdate = createdUser;
        userToUpdate.getVacations().remove(1);
        service.update(userToUpdate.getId(), userToUpdate);
        User updatedUser = service.findById(userToUpdate.getId());
        Assert.assertEquals(updatedUser.getVacations().size(), userToUpdate.getVacations().size());
    }

    @Test
    public void getPageOfUsersAndSorting_ShouldOk() {
        for (int i = 1; i <= 20; i++) {
            service.create(simpleUser(i));
        }
        Page<User> list = service.list(PageRequest.of(0, 10), null);
        Assert.assertEquals(10, list.getSize());

        Page<User> list1 = service.list(PageRequest.of(0, 20), "firstName_25");
        boolean actual = list1.stream().allMatch(u -> u.getFirstName().matches("firstName_25"));
        Assert.assertEquals(true, actual);
    }


    private User userWithVacations() {
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
        user.setVacations(new ArrayList<>());
        return user;
    }
}