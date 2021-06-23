package org.maxkizi.userdemo.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "user_info")
    private String userInfo;

    @Column(name = "user_email")
    private String userEmail;

}
