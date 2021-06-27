package org.maxkizi.userdemo.repositories;

import org.maxkizi.userdemo.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findAllByFirstName(Pageable pageable, String firstName);
    Page<User> findAll(Pageable pageable);
}
