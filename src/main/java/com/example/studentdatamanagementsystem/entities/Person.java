package com.example.studentdatamanagementsystem.entities;
import lombok.*;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
abstract class Person extends BaseClass {
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private String address;
    private String dateOfBirth;
}
