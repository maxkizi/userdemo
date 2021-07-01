package org.maxkizi.userdemo.repositories;

import com.querydsl.core.types.Predicate;
import org.maxkizi.userdemo.model.QUser;
import org.maxkizi.userdemo.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User, Long, QUser> {

    @Override
    @EntityGraph(attributePaths = {"vacations"})
    Optional<User> findById(Long id);

    @Override
    @EntityGraph(attributePaths = {"vacations"})
    Optional<User> findOne(Predicate predicate);
}
