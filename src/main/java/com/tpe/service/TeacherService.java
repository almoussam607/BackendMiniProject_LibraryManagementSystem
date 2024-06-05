package com.tpe.service;


import com.tpe.domain.Teacher;
import com.tpe.dto.TeacherDto;
import com.tpe.exception.ConflictException;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    // we have 3 types of injection in Spring Boot
    // 1-filed Injection
    // Constructor Injection
    // 3- Setter Injection

    @Autowired
    private TeacherRepository teacherRepository; // Field injection

    public Teacher saveTeacher(Teacher teacher) {
       Teacher existTeacher = teacherRepository.findByEmail(teacher.getEmail());
       if (existTeacher != null){
           throw  new ConflictException("Teacher with the same +" +teacher.getEmail() +" already Exist..");
       }
        return teacherRepository.save(teacher);


    }

    public List<Teacher> findAllTeacher() {
       List<Teacher> teacherList = teacherRepository.findAll();
       if (teacherList.isEmpty()){
           throw new ResourceNotFoundException("Teacher List is empty");
       }
       return teacherList;
    }


    public Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Teacher not found with id"+ id));
    }

    public List<Teacher> getTeacherByLastName(String lastName) {
        return teacherRepository.findByLastName(lastName);
    }

    public void deleteTeacherById(Long id) {
       Teacher teacher = getTeacherById(id);
       teacherRepository.delete(teacher);
    }

    public void updateTeacherById(Long id, Teacher teacher) {
        Teacher existTeacher = getTeacherById(id);

        if (existTeacher.getEmail().equals(teacher.getEmail())){
            Teacher teacherWithUpdateEmail = teacherRepository.findByEmail(teacher.getEmail());
            if (teacherWithUpdateEmail != null){
                throw  new ConflictException("Email already exist for another teacher. ");
            }
        }

        existTeacher.setName(teacher.getName());
        existTeacher.setLastName(teacher.getLastName());
        existTeacher.setEmail(teacher.getEmail());
        existTeacher.setPhoneNumber(teacher.getPhoneNumber());
        existTeacher.setBooks(teacher.getBooks());
        teacherRepository.save(existTeacher);

    }

    public void updateTeacherByDTO(Long teacherId, TeacherDto teacherDto) {
        Teacher existTeacher = getTeacherById(teacherId);

        if (existTeacher.getEmail().equals(teacherDto.getEmail())){
            Teacher teacherWithUpdateEmail = teacherRepository.findByEmail(teacherDto.getEmail());
            if (teacherWithUpdateEmail != null){
                throw  new ConflictException("Email already exist for another teacher. ");
            }
        }

        existTeacher.setName(teacherDto.getName());
        existTeacher.setLastName(teacherDto.getLastName());
        existTeacher.setEmail(teacherDto.getEmail());
        existTeacher.setPhoneNumber(teacherDto.getPhoneNumber());
        existTeacher.setRegisterDate(teacherDto.getRegisterDate());
        teacherRepository.save(existTeacher);

    }

    public Page<Teacher> getTeacherByPage(Pageable pageable) {
       return teacherRepository.findAll(pageable);
    }


    public List<Teacher> getTeacherByNameUsingJPQL(String name) {
        //  return teacherRepository.findByNameUsingJPQL(name);
        List<Teacher> teachers = teacherRepository.findByNameUsingJPQL(name);
        if (teachers.isEmpty()){
            throw  new ResourceNotFoundException("No Teacher Name is found .....");
        }
        return teachers;
    }

}
