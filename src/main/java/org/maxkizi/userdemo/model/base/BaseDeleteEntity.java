package org.maxkizi.userdemo.model.base;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
@SuperBuilder
@NoArgsConstructor
public class BaseDeleteEntity extends BaseEntity implements IDeleteEntity<Long>{
    @Column(name = "is_deleted")
    private boolean isDeleted;
}
