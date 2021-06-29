package org.maxkizi.userdemo.model.base;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
@SuperBuilder
@NoArgsConstructor
public class BaseNamedEntity extends BaseEntity implements INamedEntity<Long> {
    @Column(name = "name")
    private String name;

}
