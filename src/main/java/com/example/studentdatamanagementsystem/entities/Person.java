package com.example.studentdatamanagementsystem.entities;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
abstract class Person extends BaseClass {
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private String address;
    private String dateOfBirth;
}
