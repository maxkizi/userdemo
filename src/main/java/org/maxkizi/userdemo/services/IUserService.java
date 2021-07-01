package org.maxkizi.userdemo.services;

import org.maxkizi.userdemo.generated.dto.UserDto;
import org.maxkizi.userdemo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {
    Page<User> list(Pageable pageable, String search);

    User findById(Long id);

    User create(User user);

    User update(Long id, User user);

    void delete(Long id);
}
