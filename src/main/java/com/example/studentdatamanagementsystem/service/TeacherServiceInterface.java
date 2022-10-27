package com.example.studentdatamanagementsystem.service;

import com.example.studentdatamanagementsystem.dto.StudentDto;
import com.example.studentdatamanagementsystem.dto.SubjectDto;
import com.example.studentdatamanagementsystem.enums.Subjects;
import com.example.studentdatamanagementsystem.exceptions.AlreadyComputedResultException;
import com.example.studentdatamanagementsystem.exceptions.StudentAlreadyExistException;
import com.example.studentdatamanagementsystem.exceptions.StudentNotFoundException;
import com.example.studentdatamanagementsystem.exceptions.WrongSubjectCombinationException;
import com.example.studentdatamanagementsystem.response.BaseResponse;

import java.util.List;

public interface TeacherServiceInterface {
    BaseResponse<String> addStudent(StudentDto studentDto) throws StudentAlreadyExistException;
    List<?> getStudentScore(SubjectDto subjectDto) throws StudentNotFoundException;

    BaseResponse<String> calculateScore(SubjectDto subjectDto) throws StudentNotFoundException;

    List<Subjects> getStudentResultBaseOnTermAndClass(SubjectDto subjectDto) throws StudentNotFoundException;

    BaseResponse<String> getStudentResult(SubjectDto subjectDto) throws StudentNotFoundException;

    BaseResponse<String> calculateAverageScore(SubjectDto subjectDto) throws StudentNotFoundException;

    BaseResponse<String> calculateResult(SubjectDto subjectDto) throws StudentNotFoundException, AlreadyComputedResultException, IllegalAccessException;

    BaseResponse<String> computeStudentResult(SubjectDto subjectDto) throws StudentNotFoundException, ClassNotFoundException, StudentAlreadyExistException, WrongSubjectCombinationException, IllegalAccessException;
}
