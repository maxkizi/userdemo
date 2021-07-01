package org.maxkizi.userdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.maxkizi.userdemo.model.base.BaseDeleteNamedEntity;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "users")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseDeleteNamedEntity {

    private String firstName;
    private String lastName;
    private String userInfo;
    private String userEmail;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    @OrderBy(value = "id")
    private List<UserVacation> vacations;

}
