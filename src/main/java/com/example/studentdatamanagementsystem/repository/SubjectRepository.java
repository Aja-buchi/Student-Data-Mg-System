package com.example.studentdatamanagementsystem.repository;

import com.example.studentdatamanagementsystem.entities.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subjects, Long> {

    Optional<Subjects> findSubjectsByStudentName(String name);

    Optional<Subjects> findSubjectsByAverageScore(double averageScore);

    Optional<Subjects> findSubjectsByTotalScore(int totalScore);

    List<Subjects> findAllByTermsAndStudentClass(String term, String studentClass);

    Optional<Subjects> findStudentSubjectsByStudentClass(String studentClass);
}
