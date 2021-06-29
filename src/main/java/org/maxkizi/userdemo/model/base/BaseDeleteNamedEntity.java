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
public class BaseDeleteNamedEntity extends BaseNamedEntity implements IDeleteEntity<Long> {
    @Column(name = "is_deleted")
    private boolean deleted;
}
