package com.example.studentdatamanagementsystem.service;

import com.example.studentdatamanagementsystem.dto.StudentDto;
import com.example.studentdatamanagementsystem.enums.Results;
import com.example.studentdatamanagementsystem.enums.Subjects;
import com.example.studentdatamanagementsystem.enums.Terms;
import com.example.studentdatamanagementsystem.response.BaseResponse;

public interface TeacherServiceInterface {
    BaseResponse<String> getStudentScore(Terms term);

    BaseResponse<String> createStudent(StudentDto studentDto);

    BaseResponse<String> addScore(StudentDto studentDto);

    BaseResponse<String> addResult(Results results);

    BaseResponse<String> getStudentScore(Subjects subject);

    BaseResponse<String> getStudentAvgScore(StudentDto studentDto);

}
