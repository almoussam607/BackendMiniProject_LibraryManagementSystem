package com.tpe.dto;


import com.tpe.domain.Teacher;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDto {

    private Long id;


    @NotNull(message = "Name Can not be Null ")
    @NotBlank(message = "Name can not be white space ")
    @Size(min = 4,max = 25,message = "name '${validateValue}' must be between :{min} and {max} ")
    private String name;

    private String lastName;

    @Email(message = "Please Provide the valid email")// frotan@123gmail.com

    private String email;

    private String phoneNumber;

    private LocalDateTime registerDate= LocalDateTime.now();

    public TeacherDto(Teacher teacher) {
        this.id=teacher.getId();
        this.name=teacher.getName();
        this.lastName=teacher.getLastName();
        this.email=teacher.getEmail();
        this.phoneNumber=teacher.getPhoneNumber();
        this.registerDate=teacher.getRegisterDate();
    }

}