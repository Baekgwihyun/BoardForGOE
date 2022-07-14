package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "BOARDFORGOE")
public class Board {
    /*@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String goe_user_name;
    private String goe_user_phone;

    private String goe_title;

    private String goe_school_name;
    private String goe_help_content;
    private LocalDateTime goe_help_date;

}*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "GOE_USER_NAME")
    private String goeUserName;
    @Column(name = "GOE_USER_PHONE")
    private String goeUserPhone;
    @Column(name = "GOE_TITLE")
    private String goeTitle;
    @Column(name = "GOE_SCHOOL_NAME")
    private String goeSchoolName;
    @Column(name = "GOE_HELP_CONTENT")
    private String goeHelpContent;
    @Column(name = "GOE_HELP_DATE")
    private LocalDateTime goeHelpDate;



}
