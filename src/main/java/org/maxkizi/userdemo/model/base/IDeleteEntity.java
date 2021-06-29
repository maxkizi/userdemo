package org.maxkizi.userdemo.model.base;

import java.io.Serializable;

public interface IDeleteEntity<T extends Serializable> extends IEntity<Long> {

    boolean isDeleted();

    void setDeleted(boolean deleted);
}
