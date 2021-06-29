package org.maxkizi.userdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.maxkizi.userdemo.model.base.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "user_vacations")
@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserVacation extends BaseEntity {

    @Column(name = "date_from")
    private LocalDate dateFrom;
    @Column(name = "date_to")
    private LocalDate dateTo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
