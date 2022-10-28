package com.example.studentdatamanagementsystem.controller;

import com.example.studentdatamanagementsystem.dto.StudentDto;
import com.example.studentdatamanagementsystem.dto.SubjectDto;
import com.example.studentdatamanagementsystem.exceptions.AlreadyComputedResultException;
import com.example.studentdatamanagementsystem.exceptions.StudentAlreadyExistException;
import com.example.studentdatamanagementsystem.exceptions.StudentNotFoundException;
import com.example.studentdatamanagementsystem.exceptions.WrongSubjectCombinationException;
import com.example.studentdatamanagementsystem.response.BaseResponse;
import com.example.studentdatamanagementsystem.service.serviceImpl.TeacherServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TeacherController {

    private final TeacherServiceImpl teacherService;

    @PostMapping("/createStudent")
    public BaseResponse<String> addStudent(@RequestBody StudentDto studentDto) throws StudentAlreadyExistException {
        return teacherService.createStudent(studentDto);
    }
    @PostMapping("/addStudentRecord")
    public BaseResponse<String> addStudentRecord(@RequestBody SubjectDto subjectDto) throws StudentAlreadyExistException, StudentNotFoundException, IllegalAccessException, ClassNotFoundException, WrongSubjectCombinationException {
        return teacherService.computeStudentResult(subjectDto);
    }
    @PostMapping("/addTotalScore")
    public BaseResponse<String> addTotalScore(@RequestBody SubjectDto subjectDto) throws StudentNotFoundException {
        return teacherService.computeScore(subjectDto);
    }
    @PostMapping("/computeAverageScore")
    public BaseResponse<String> calculateAverageScore(@RequestBody SubjectDto subjectDto) throws StudentNotFoundException {
        return teacherService.computeAverageScore(subjectDto);
    }
    @PostMapping("/computeResult")
    public BaseResponse<String> calculateResult(@RequestBody SubjectDto subjectDto) throws StudentNotFoundException, AlreadyComputedResultException, IllegalAccessException {
        return teacherService.computeResult(subjectDto);
    }
    @GetMapping("/getStudentScore")
    public BaseResponse<String> getStudentResult(@RequestBody SubjectDto subjectDto) throws StudentNotFoundException {
        return teacherService.getStudentResult(subjectDto);
    }
    @GetMapping("/getAllStudentScore")
    public List<?> getAllStudentScore(@RequestBody SubjectDto subjectDto) throws StudentNotFoundException {
        return teacherService.getStudentScore(subjectDto);
    }
    @GetMapping("/getStudentResultByTerm")
    public BaseResponse<String> getStudentResultBaseOnTermAndClass(@RequestBody SubjectDto subjectDto) throws StudentNotFoundException {
        return teacherService.getStudentResult(subjectDto);
    }
}
