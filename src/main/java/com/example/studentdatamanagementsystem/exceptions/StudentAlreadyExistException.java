package com.example.studentdatamanagementsystem.exceptions;

public class StudentAlreadyExistException extends RuntimeException{
    public StudentAlreadyExistException(String message){
        super(message);
    }
}
