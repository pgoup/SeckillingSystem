package com.application.SeckillingSystem.BaseModule.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id",unique = true)
    private String userId;

    @Column(name = "user_name")
    private String userName;

    private String address;

    private String telephone;
}

