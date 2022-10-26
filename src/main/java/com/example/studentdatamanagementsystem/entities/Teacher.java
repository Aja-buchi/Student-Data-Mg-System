package com.example.studentdatamanagementsystem.entities;

import lombok.*;
import javax.persistence.Entity;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Teacher extends Person{
    private String officeLocation;
}
