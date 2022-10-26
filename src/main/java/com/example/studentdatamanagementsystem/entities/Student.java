package com.example.studentdatamanagementsystem.entities;

import com.example.studentdatamanagementsystem.enums.*;
import lombok.*;

import javax.persistence.Entity;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student extends Person{
    private Classes studentClass;
    private Subjects subject;
    private Terms term;
    private String results;
    private String score;
}
