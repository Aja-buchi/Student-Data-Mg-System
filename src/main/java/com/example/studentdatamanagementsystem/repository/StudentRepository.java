package com.example.studentdatamanagementsystem.repository;
import com.example.studentdatamanagementsystem.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findStudentsByEmail(String email);
    Optional<Student> findStudentsByStudentClass(String studentClass);
}
