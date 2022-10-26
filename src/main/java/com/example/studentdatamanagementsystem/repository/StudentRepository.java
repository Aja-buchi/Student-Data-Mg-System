package com.example.studentdatamanagementsystem.repository;

import com.example.studentdatamanagementsystem.entities.Student;
import com.example.studentdatamanagementsystem.enums.Classes;
import com.example.studentdatamanagementsystem.enums.Subjects;
import com.example.studentdatamanagementsystem.enums.Terms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository {

    Optional<Student> findStudentResultBySubject(Subjects subjects);
    Optional<Student> findStudentResultByStudentClass(Classes studentClass);
    Optional<Student> findStudentResultByTerm(Terms term);
}
