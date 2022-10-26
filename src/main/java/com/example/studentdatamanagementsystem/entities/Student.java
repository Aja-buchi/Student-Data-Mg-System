package com.example.studentdatamanagementsystem.entities;

import com.example.studentdatamanagementsystem.enums.*;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student extends Person{
    private Classes studentClass;
    private Subjects subject;
    private Terms term;
    private String results;
    private String score;
}
