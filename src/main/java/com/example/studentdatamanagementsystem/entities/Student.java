package com.example.studentdatamanagementsystem.entities;

import com.example.studentdatamanagementsystem.enums.*;
import lombok.*;

import javax.persistence.Entity;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student extends Person{
    private String studentClass;
//    private Subjects subject;
//    private Terms term;
//    private Results results;
//    private String score;
//    private String totalScore;
}
