package org.maxkizi.userdemo.services;


import com.querydsl.core.types.Predicate;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.maxkizi.userdemo.model.base.IEntity;

public interface BaseService<T extends IEntity<I>, I extends Serializable> {

    Optional<T> get(I id);

    Page<T> findAll(Predicate predicate, Pageable pageable);

    List<T> findAll();

    T save(T t);

    void delete(T t);

    void delete(I id);

    boolean isExist(I id);

    Optional<T> get(Predicate predicate);
}
