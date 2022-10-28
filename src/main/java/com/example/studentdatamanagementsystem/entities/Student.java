package com.example.studentdatamanagementsystem.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student extends Person{
    private String studentClass;

    @OneToMany(targetEntity = Subjects.class, mappedBy = "id")
    private List<Subjects> listOfSubjects;
}
