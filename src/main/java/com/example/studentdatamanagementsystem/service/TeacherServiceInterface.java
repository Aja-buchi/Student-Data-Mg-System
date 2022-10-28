package com.example.studentdatamanagementsystem.service;

import com.example.studentdatamanagementsystem.dto.StudentDto;
import com.example.studentdatamanagementsystem.dto.SubjectDto;
import com.example.studentdatamanagementsystem.entities.Subjects;
import com.example.studentdatamanagementsystem.exceptions.AlreadyComputedResultException;
import com.example.studentdatamanagementsystem.exceptions.StudentAlreadyExistException;
import com.example.studentdatamanagementsystem.exceptions.StudentNotFoundException;
import com.example.studentdatamanagementsystem.exceptions.WrongSubjectCombinationException;
import com.example.studentdatamanagementsystem.response.BaseResponse;

import java.util.List;

public interface TeacherServiceInterface {
    BaseResponse<String> createStudent(StudentDto studentDto) throws StudentAlreadyExistException;

    List<?> getStudentScore(SubjectDto subjectDto) throws StudentNotFoundException;

    BaseResponse<String> computeScore(SubjectDto subjectDto) throws StudentNotFoundException;

    List<Subjects> getStudentResultByTermAndClass(SubjectDto subjectDto) throws StudentNotFoundException;

    BaseResponse<String> getStudentResult(SubjectDto subjectDto) throws StudentNotFoundException;

    BaseResponse<String> computeAverageScore(SubjectDto subjectDto) throws StudentNotFoundException;

    BaseResponse<String> computeResult(SubjectDto subjectDto) throws StudentNotFoundException, AlreadyComputedResultException, IllegalAccessException;

    BaseResponse<String> computeStudentResult(SubjectDto subjectDto) throws StudentNotFoundException, ClassNotFoundException, StudentAlreadyExistException, WrongSubjectCombinationException, IllegalAccessException;
}
