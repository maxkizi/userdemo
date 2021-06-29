package org.maxkizi.userdemo.controllers;

import com.datical.liquibase.ext.storedlogic.databasepackage.PackageBodySnapshotGenerator;
import lombok.AllArgsConstructor;
import org.maxkizi.userdemo.generated.dto.UserDto;
import org.maxkizi.userdemo.generated.dto.UserShortDto;
import org.maxkizi.userdemo.services.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class UserController {
    private final IUserService service;

    @GetMapping("/users")
    public Page<UserShortDto> list(Pageable pageable,
                                   @RequestParam(name = "search", required = false) String search) {
        return service.list(pageable, search);
    }

    @GetMapping("user/{id}")
    public UserDto getById(@PathVariable(name = "id") Long id) {
        return service.findById(id);
    }

    @PostMapping("/users")
    public UserDto create(@RequestBody UserDto userDto) {
        return service.create(userDto);
    }

    @PutMapping("/user/{id}")
    public UserDto update(@PathVariable(name = "id") Long id,
                          @RequestBody UserDto userDto) {
        return service.update(id, userDto);
    }

    @DeleteMapping("user/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
