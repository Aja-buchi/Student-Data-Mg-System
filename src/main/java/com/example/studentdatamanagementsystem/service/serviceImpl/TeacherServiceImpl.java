package com.example.studentdatamanagementsystem.service.serviceImpl;

import com.example.studentdatamanagementsystem.dto.StudentDto;
import com.example.studentdatamanagementsystem.dto.SubjectDto;
import com.example.studentdatamanagementsystem.entities.Student;
import com.example.studentdatamanagementsystem.entities.Subjects;
import com.example.studentdatamanagementsystem.enums.Results;
import com.example.studentdatamanagementsystem.enums.Terms;
import com.example.studentdatamanagementsystem.exceptions.AlreadyComputedResultException;
import com.example.studentdatamanagementsystem.exceptions.StudentAlreadyExistException;
import com.example.studentdatamanagementsystem.exceptions.StudentNotFoundException;
import com.example.studentdatamanagementsystem.exceptions.WrongSubjectCombinationException;
import com.example.studentdatamanagementsystem.repository.StudentRepository;
import com.example.studentdatamanagementsystem.repository.SubjectRepository;
import com.example.studentdatamanagementsystem.response.BaseResponse;
import com.example.studentdatamanagementsystem.service.TeacherServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherServiceInterface {
    private final StudentRepository studentRepository;

    private final SubjectRepository subjectRepository;

    @Override
    public BaseResponse<String> createStudent(StudentDto studentDto) throws StudentAlreadyExistException {
        Student students = new Student();
        boolean studentExist = studentRepository.findStudentsByEmail(studentDto.getEmail()).isPresent();
        if(studentExist){
            throw new StudentAlreadyExistException("Student with email Already Exist");
        }
        students.setFirstname(studentDto.getFirstname());
        students.setLastname(studentDto.getLastname());
        students.setEmail(studentDto.getEmail());
        students.setPhoneNumber(studentDto.getPhoneNumber());
        students.setAddress(studentDto.getAddress());
        students.setDateOfBirth(studentDto.getDateOfBirth());
        students.setStudentClass(studentDto.getStudentClass());
        BeanUtils.copyProperties(studentDto, students);
        studentRepository.save(students);
        return new BaseResponse<>(HttpStatus.OK,"Successfully Added Student",null);
    }

    @Override
    public List<?> getStudentScore(SubjectDto subjectDto) throws StudentNotFoundException {

        Subjects subjects =
                subjectRepository.findSubjectsByStudentName(subjectDto.getStudentName()).
                        orElseThrow(()-> new StudentNotFoundException("Student with name not found"));
        List<Subjects> result = new ArrayList<>();
        if((subjects != null) && (subjects.getTerms().equals(subjectDto.getTerm()))){
            result = subjectRepository.findAll();
        }
        return result;
    }

    @Override
    public BaseResponse<String> computeScore(SubjectDto subjectDto) throws StudentNotFoundException {
        Subjects subjects =
                subjectRepository.findSubjectsByStudentName(subjectDto.getStudentName()).
                        orElseThrow(()-> new StudentNotFoundException("Student with name not found"));
        if(subjects != null && subjects.getStudentClass().equals("CLASS_A1")){
            int  totalScore  = subjects.getEnglish() +
                    subjects.getMathematics() +
                    subjects.getGeneralScience() + subjects.getWriting();
            subjects.setTotalScore(totalScore);
            subjectRepository.save(subjects);
        }
        else if(subjects != null && subjects.getStudentClass().equals("CLASS_A2")){
            int  totalScore  = subjects.getEnglish() +
                    subjects.getMathematics() +
                    subjects.getEconomics() + subjects.getArt();
            subjects.setTotalScore(totalScore);
            subjectRepository.save(subjects);
        }
        else if(subjects != null && subjects.getStudentClass().equals("CLASS_B1")){
            int  totalScore  =
                    subjects.getMathematics() +
                            subjects.getEnglish()+
                            subjects.getBiology()+
                            subjects.getChemistry()+
                            subjects.getPhysics()+
                            subjects.getGeography();
            subjects.setTotalScore(totalScore);
            subjectRepository.save(subjects);
        }

        else if(subjects != null && subjects.getStudentClass().equals("CLASS_B2")){
            int  totalScore  =
                    subjects.getMathematics() +
                            subjects.getEnglish()+
                            subjects.getDrawing()+
                            subjects.getMusic()+
                            subjects.getPainting()+
                            subjects.getAccounting();
            subjects.setTotalScore(totalScore);
            subjectRepository.save(subjects);
        }
        return new BaseResponse<>(HttpStatus.OK,"Successfully Computed Total Score", null);
    }

    @Override
    public List<Subjects> getStudentResultByTermAndClass(SubjectDto subjectDto) throws StudentNotFoundException {
        Subjects studentSubjects =
                subjectRepository.findSubjectsByStudentName(subjectDto.getStudentName()).
                        orElseThrow(()-> new StudentNotFoundException("Student with name not found"));
        List<Subjects> result = new ArrayList<>();
        if(studentSubjects != null && studentSubjects.getStudentName().equals(subjectDto.getStudentName())){
            result = subjectRepository.
                    findAllByTermsAndStudentClass(subjectDto.getStudentClass(), subjectDto.getTerm());
        }

        return result;
    }

    @Override
    public BaseResponse<String> getStudentResult(SubjectDto subjectDto) throws StudentNotFoundException {
        Subjects studentSubjects =
                subjectRepository.findSubjectsByStudentName(subjectDto.getStudentName()).
                        orElseThrow(()-> new StudentNotFoundException("Student with name not found"));
        if(studentSubjects != null && subjectDto.getSubjectName().equals("mathematics") &&
                studentSubjects.getStudentClass().equals("CLASS_A1")){
            return new BaseResponse<>(HttpStatus.OK,"Successfully Retrieved Result", String.valueOf(studentSubjects.getMathematics()));
        }
        else if(studentSubjects != null && subjectDto.getSubjectName().equals("english") &&
                studentSubjects.getStudentClass().equals("CLASS_A1")){
            return new BaseResponse<>(HttpStatus.OK,"Successfully Retrieved Result", String.valueOf(studentSubjects.getEnglish()));
        }
        else if(studentSubjects != null && subjectDto.getSubjectName().equals("writing") &&
                studentSubjects.getStudentClass().equals("CLASS_A1")){
            return new BaseResponse<>(HttpStatus.OK,"Successfully Retrieved Result", String.valueOf(studentSubjects.getWriting()));
        }
        else if(studentSubjects != null && subjectDto.getSubjectName().equals("generalScience") &&
                studentSubjects.getStudentClass().equals("CLASS_A1")){
            return new BaseResponse<>(HttpStatus.OK,"Successfully Retrieved Result", String.valueOf(studentSubjects.getGeneralScience()));
        }
        if(studentSubjects != null && subjectDto.getSubjectName().equals("mathematics") &&
                studentSubjects.getStudentClass().equals("CLASS_A2")){
            return new BaseResponse<>(HttpStatus.OK,"Successfully Retrieved Result", String.valueOf(studentSubjects.getMathematics()));
        }
        else if(studentSubjects != null && subjectDto.getSubjectName().equals("english") &&
                studentSubjects.getStudentClass().equals("CLASS_A2")){
            return new BaseResponse<>(HttpStatus.OK,"Successfully Retrieved Result", String.valueOf(studentSubjects.getEnglish()));
        }
        else if(studentSubjects != null && subjectDto.getSubjectName().equals("economics") &&
                studentSubjects.getStudentClass().equals("CLASS_A2")){
            return new BaseResponse<>(HttpStatus.OK,"Successfully Retrieved Result", String.valueOf(studentSubjects.getEconomics()));
        }
        else if(studentSubjects != null && subjectDto.getSubjectName().equals("art") &&
                studentSubjects.getStudentClass().equals("CLASS_A2")){
            return new BaseResponse<>(HttpStatus.OK,"Successfully Retrieved Result", String.valueOf(studentSubjects.getArt()));
        }
        //Retrieved Result for a specific Student in CLASS_B1//
        if(studentSubjects != null && subjectDto.getSubjectName().equals("mathematics") &&
                studentSubjects.getStudentClass().equals("CLASS_B1")){
            return new BaseResponse<>(HttpStatus.OK,"Successfully Retrieved Result", String.valueOf(studentSubjects.getMathematics()));
        }
        else if(studentSubjects != null && subjectDto.getSubjectName().equals("english") &&
                studentSubjects.getStudentClass().equals("CLASS_B1")){
            return new BaseResponse<>(HttpStatus.OK,"Successfully Retrieved Result", String.valueOf(studentSubjects.getEnglish()));
        }
        else if(studentSubjects != null && subjectDto.getSubjectName().equals("biology") &&
                studentSubjects.getStudentClass().equals("CLASS_B1")){
            return new BaseResponse<>(HttpStatus.OK,"Successfully Retrieved Result", String.valueOf(studentSubjects.getBiology()));
        }
        else if(studentSubjects != null && subjectDto.getSubjectName().equals("physics") &&
                studentSubjects.getStudentClass().equals("CLASS_B1")){
            return new BaseResponse<>(HttpStatus.OK,"Successfully Retrieved Result", String.valueOf(studentSubjects.getPhysics()));
        }
        else if(studentSubjects != null && subjectDto.getSubjectName().equals("chemistry") &&
                studentSubjects.getStudentClass().equals("CLASS_B1")){
            return new BaseResponse<>(HttpStatus.OK,"Successfully Retrieved Result", String.valueOf(studentSubjects.getChemistry()));
        }
        else if(studentSubjects != null && subjectDto.getSubjectName().equals("geography") &&
                studentSubjects.getStudentClass().equals("CLASS_B1")){
            return new BaseResponse<>(HttpStatus.OK,"Successfully Retrieved Result", String.valueOf(studentSubjects.getGeography()));
        }
        if(studentSubjects != null && subjectDto.getSubjectName().equals("mathematics") &&
                studentSubjects.getStudentClass().equals("CLASS_B2")){
            return new BaseResponse<>(HttpStatus.OK,"Successfully Retrieved Result", String.valueOf(studentSubjects.getMathematics()));
        }
        else if(studentSubjects != null && subjectDto.getSubjectName().equals("english") &&
                studentSubjects.getStudentClass().equals("CLASS_B2")){
            return new BaseResponse<>(HttpStatus.OK,"Successfully Retrieved Result", String.valueOf(studentSubjects.getEnglish()));
        }
        else if(studentSubjects != null && subjectDto.getSubjectName().equals("painting") &&
                studentSubjects.getStudentClass().equals("CLASS_B2")){
            return new BaseResponse<>(HttpStatus.OK,"Successfully Retrieved Result", String.valueOf(studentSubjects.getPainting()));
        }
        else if(studentSubjects != null && subjectDto.getSubjectName().equals("drawing") &&
                studentSubjects.getStudentClass().equals("CLASS_B2")){
            return new BaseResponse<>(HttpStatus.OK,"Successfully Retrieved Result", String.valueOf(studentSubjects.getDrawing()));
        }
        else if(studentSubjects != null && subjectDto.getSubjectName().equals("music") &&
                studentSubjects.getStudentClass().equals("CLASS_B2")){
            return new BaseResponse<>(HttpStatus.OK,"Successfully Retrieved Result", String.valueOf(studentSubjects.getMusic()));
        }
        else if(studentSubjects != null && subjectDto.getSubjectName().equals("accounting") &&
                studentSubjects.getStudentClass().equals("CLASS_B2")){
            return new BaseResponse<>(HttpStatus.OK,"Successfully Retrieved Result", String.valueOf(studentSubjects.getAccounting()));
        }
        return null;
    }

    @Override
    public BaseResponse<String> computeAverageScore(SubjectDto subjectDto) throws StudentNotFoundException {
        Subjects studentSubjects =
                subjectRepository.findSubjectsByStudentName(subjectDto.getStudentName()).
                        orElseThrow(()-> new StudentNotFoundException("Student with name not found"));
        Subjects totalScore = subjectRepository.findSubjectsByTotalScore(studentSubjects.getTotalScore()).
                orElseThrow(()-> new NullPointerException("This value is Empty"));
        if(totalScore != null && studentSubjects.getStudentClass().equals("CLASS_A1")){
            Double averageScore = (double) (studentSubjects.getTotalScore() / 4);
            studentSubjects.setAverageScore(averageScore);
            subjectRepository.save(studentSubjects);
        }
        else if(totalScore != null && studentSubjects.getStudentClass().equals("CLASS_A2")){
            Double averageScore = (double) (studentSubjects.getTotalScore() / 4);
            studentSubjects.setAverageScore(averageScore);
            subjectRepository.save(studentSubjects);
        }
        else if(totalScore != null && studentSubjects.getStudentClass().equals("CLASS_B1")){
            Double averageScore = (double) (studentSubjects.getTotalScore() / 6);
            studentSubjects.setAverageScore(averageScore);
            subjectRepository.save(studentSubjects);
        }
        else if(totalScore != null && studentSubjects.getStudentClass().equals("CLASS_B2")){
            Double averageScore = (double) (studentSubjects.getTotalScore() / 6);
            studentSubjects.setAverageScore(averageScore);
            subjectRepository.save(studentSubjects);
        }
        return new BaseResponse<>(HttpStatus.OK,"Successfully Computed Average Score", null);
    }

    @Override
    public BaseResponse<String> computeResult(SubjectDto subjectDto) throws StudentNotFoundException, AlreadyComputedResultException, IllegalAccessException {
        Subjects subjects =
                subjectRepository.findSubjectsByStudentName(subjectDto.getStudentName()).
                        orElseThrow(()-> new StudentNotFoundException("Student with name not found"));

        Subjects averageScore = subjectRepository.findSubjectsByAverageScore(subjects.getAverageScore()).
                orElseThrow(()-> new NullPointerException("This value is Empty"));
        if(subjects.getStudentResult() != null){
            throw new AlreadyComputedResultException("Student's result already computed");
        }
        if(averageScore != null){
            Double totalAverageScore = subjects.getAverageScore();
            if(totalAverageScore <50 ){
                subjects.setStudentResult(String.valueOf(Results.POOR));
                subjectRepository.save(subjects);
            }
            else if(totalAverageScore >=50 && totalAverageScore <=65 ){
                subjects.setStudentResult(String.valueOf(Results.GOOD));
                subjectRepository.save(subjects);
            }
            else if(totalAverageScore >65 && totalAverageScore <=80 ){
                subjects.setStudentResult(String.valueOf(Results.VERY_GOOD));
                subjectRepository.save(subjects);
            }
            else if(totalAverageScore >80 && totalAverageScore <=100 ){
                subjects.setStudentResult(String.valueOf(Results.EXCELLENT));
                subjectRepository.save(subjects);
            }
        }else{
            throw new IllegalAccessException("Total Average Value is Null compute it first");
        }
        return new BaseResponse<>(HttpStatus.OK,"Student Result Computed Successfully", null);
    }

    @Override
    public BaseResponse<String> computeStudentResult(SubjectDto subjectDto) throws StudentNotFoundException, ClassNotFoundException, StudentAlreadyExistException, WrongSubjectCombinationException, IllegalAccessException {
        boolean studentExist = subjectRepository.findSubjectsByStudentName(subjectDto.getStudentName()).isPresent();
        if(studentExist){
            throw new StudentAlreadyExistException("Student Name Already Exist");
        }
        Subjects studentSubjects = new Subjects();
        if(subjectDto.getStudentClass().equals("CLASS_A1")) {
            if(subjectDto.getMathematics() != null && subjectDto.getEnglish()
                    != null && subjectDto.getWriting() != null
                    && subjectDto.getGeneralScience() !=null) {
                studentSubjects.setMathematics(subjectDto.getMathematics());
                studentSubjects.setStudentName(subjectDto.getStudentName());
                studentSubjects.setEnglish(subjectDto.getEnglish());
                studentSubjects.setWriting(subjectDto.getWriting());
                studentSubjects.setGeneralScience(subjectDto.getGeneralScience());
                studentSubjects.setStudentClass(subjectDto.getStudentClass());
                studentSubjects.setTerms(Terms.FIRST_TERM);
                subjectRepository.save(studentSubjects);
            }else {
                throw new WrongSubjectCombinationException("This Subject Is Not Meant To Be in CLASS_A1");
            }
        }
        else if(subjectDto.getStudentClass().equals("CLASS_A2")){
            if(subjectDto.getMathematics() != null && subjectDto.getEnglish()
                    != null && subjectDto.getEconomics() != null
                    && subjectDto.getArt() !=null) {
                studentSubjects.setArt(subjectDto.getArt());
                studentSubjects.setStudentName(subjectDto.getStudentName());
                studentSubjects.setEconomics(subjectDto.getEconomics());
                studentSubjects.setEnglish(subjectDto.getEnglish());
                studentSubjects.setMathematics(subjectDto.getMathematics());
                studentSubjects.setStudentClass(subjectDto.getStudentClass());
                studentSubjects.setTerms(Terms.FIRST_TERM);
                subjectRepository.save(studentSubjects);
            }else {
                throw new WrongSubjectCombinationException("This Subject Is Not Meant To Be in CLASS_A2");
            }
        }
        else if(subjectDto.getStudentClass().equals("CLASS_B1")) {
            if(subjectDto.getMathematics() != null && subjectDto.getEnglish()
                    != null && subjectDto.getBiology() != null
                    && subjectDto.getPhysics()
                    !=null && subjectDto.getChemistry() != null
                    && subjectDto.getGeography() !=null ) {
                studentSubjects.setStudentName(subjectDto.getStudentName());
                studentSubjects.setEnglish(subjectDto.getEnglish());
                studentSubjects.setMathematics(subjectDto.getMathematics());
                studentSubjects.setBiology(subjectDto.getBiology());
                studentSubjects.setPhysics(subjectDto.getPhysics());
                studentSubjects.setChemistry(subjectDto.getChemistry());
                studentSubjects.setGeography(subjectDto.getGeography());
                studentSubjects.setStudentClass(subjectDto.getStudentClass());
                studentSubjects.setTerms(Terms.FIRST_TERM);
                subjectRepository.save(studentSubjects);
            }else {
                throw new WrongSubjectCombinationException("This Subject Is Not Meant To Be in CLASS_B1");
            }
        }
        else if (subjectDto.getStudentClass().equals("CLASS_B2")){
            if(subjectDto.getMathematics() != null && subjectDto.getEnglish()
                    != null && subjectDto.getDrawing() != null
                    && subjectDto.getMusic()
                    !=null && subjectDto.getPainting() != null
                    && subjectDto.getAccounting() !=null ) {
                studentSubjects.setStudentName(subjectDto.getStudentName());
                studentSubjects.setEnglish(subjectDto.getEnglish());
                studentSubjects.setMathematics(subjectDto.getMathematics());
                studentSubjects.setDrawing(subjectDto.getDrawing());
                studentSubjects.setMusic(subjectDto.getMusic());
                studentSubjects.setPainting(subjectDto.getPainting());
                studentSubjects.setAccounting(subjectDto.getAccounting());
                studentSubjects.setStudentClass(subjectDto.getStudentClass());
                studentSubjects.setTerms(Terms.FIRST_TERM);
                subjectRepository.save(studentSubjects);
            }else {
                throw new WrongSubjectCombinationException("This Subject Is Not Meant To Be in CLASS_B2");
            }
        }
        return new BaseResponse<>(HttpStatus.OK,"Successfully Added Record",null);
    }
}
