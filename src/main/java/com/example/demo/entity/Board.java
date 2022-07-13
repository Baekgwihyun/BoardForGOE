package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "BOARDFORGOE")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String goe_user_name;
    private String goe_user_phone;

    private String goe_title;

    private String goe_school_name;
    private String goe_help_content;
    private LocalDateTime goe_help_date;

}

//    private Long id;
//
//    private String goeUserName;
//    private String goeUserPhone;
//
//    private String goeTitle;
//
//    private String goeSchoolName;
//    private String goeHelpContent;
//    private LocalDateTime goeHelpDate;
//
//
//
//}
