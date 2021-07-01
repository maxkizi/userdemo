package org.maxkizi.userdemo.services.impl;

import com.querydsl.core.BooleanBuilder;
import liquibase.pro.packaged.B;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.maxkizi.userdemo.converter.UserConverter;
import org.maxkizi.userdemo.exceptions.UserNotFoundException;
import org.maxkizi.userdemo.model.QUser;
import org.maxkizi.userdemo.model.User;
import org.maxkizi.userdemo.repositories.UserRepository;
import org.maxkizi.userdemo.services.AbstractBaseService;
import org.maxkizi.userdemo.services.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@AllArgsConstructor
@Getter
@Slf4j
public class UserServiceImpl extends AbstractBaseService<User, Long, QUser, UserRepository> implements IUserService {

    private final UserRepository repository;
    private final UserConverter converter;

    @Override
    public Page<User> list(Pageable pageable, String search) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(QUser.user.deleted.isFalse());
        if (StringUtils.hasText(search)) {
            booleanBuilder.and(QUser.user.name.containsIgnoreCase(search))
                    .or(QUser.user.firstName.containsIgnoreCase(search));
        }
        return findAll(booleanBuilder, pageable);
    }

    @Override
    public User findById(Long id) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(QUser.user.deleted.isFalse());
        booleanBuilder.and(QUser.user.id.eq(id));
        return get(booleanBuilder).orElseThrow(UserNotFoundException::new);
    }


    @Override
    public User create(User user) {
        return save(user);
    }

    @Override
    public User update(Long id, User user) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(QUser.user.deleted.isFalse());
        booleanBuilder.and(QUser.user.id.eq(id));
        User oldUser = get(booleanBuilder).orElseThrow(UserNotFoundException::new);
        user.setId(id);
        user.setCreatedAt(oldUser.getCreatedAt());
        return save(user);
    }

    @Override
    public void delete(Long id) {
        User user = get(id).orElseThrow(UserNotFoundException::new);
        user.setDeleted(true);
        save(user);
    }
}
