package org.maxkizi.userdemo.repositories;

import org.maxkizi.userdemo.model.QUser;
import org.maxkizi.userdemo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, Long, QUser> {

}
