package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String goe_user_name;
    private String goe_user_phone;

    private String goe_title;

    private String goe_school_name;
    private String goe_help_content;
    private String help_insert_date;
}
