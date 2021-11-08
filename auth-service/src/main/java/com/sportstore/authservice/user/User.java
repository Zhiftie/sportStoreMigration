package com.sportstore.authservice.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @SequenceGenerator(name="user_user_id_seq",
            sequenceName="user_user_id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="user_user_id_seq")
    private Long userId;
    private String name;
    private String password;
    private String tenant;

    public User() {}

}

