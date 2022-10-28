package com.example.studentdatamanagementsystem.entities;

import com.example.studentdatamanagementsystem.enums.Terms;
import lombok.*;

import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
public class Subjects extends BaseClass{

    private String studentName;

    private String studentResult;

    private String studentClass;

    @Enumerated(EnumType.STRING)
    private Terms terms;

    private Integer mathematics;

    private Integer english;

    private Integer chemistry;

    private Integer biology;

    private Integer physics;

    private Integer economics;

    private Integer accounting;

    private Integer drawing;

    private Integer geography;

    private Integer writing;

    private Integer music;

    private Integer painting;

    private Integer art;

    private Integer generalScience;

    private Integer totalScore;

    private Double averageScore;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    Student student;
}
