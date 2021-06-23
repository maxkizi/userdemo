package org.maxkizi.userdemo.controllers;


import org.maxkizi.userdemo.generated.dto.FullUserDto;
import org.maxkizi.userdemo.generated.dto.PartUserDto;
import org.maxkizi.userdemo.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user-api")
public class UserController {

    private final UserServiceImpl service;

    @Autowired
    public UserController(UserServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<PartUserDto> list(@RequestParam(name = "lastName", required = false) String lastName) {
        if (lastName != null) {
            return service.list().stream().filter(u -> u.getLastName().equals(lastName)).collect(Collectors.toList());
        } else {
            return service.list();
        }
    }

    @GetMapping("/user/{id}")
    public FullUserDto getUserById(@PathVariable(name = "id") Long userId) {
        return service.findById(userId);
    }

    @PostMapping("/users")
    public FullUserDto create(@RequestBody FullUserDto user) {
        return service.create(user);
    }

    @PutMapping("/user/{id}")
    public FullUserDto update(@PathVariable(name = "id") Long id,
                              @RequestBody FullUserDto user) {
        return service.update(id, user);
    }

    @DeleteMapping("user/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
