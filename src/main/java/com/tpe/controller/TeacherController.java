package com.tpe.controller;


import com.tpe.domain.Teacher;
import com.tpe.dto.TeacherDto;
import com.tpe.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController //http://localhost:8080
@RequestMapping("/teachers") //http://localhost:8080/teachers
public class TeacherController {


    @Autowired
    private TeacherService teacherService;

    @PostMapping //http://localhost:8080/teachers
    public ResponseEntity<Map<String, String>> saveTeacher(@RequestBody Teacher teacher){
        Teacher teacher1 = teacherService.saveTeacher(teacher);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Teacher has been saved successfully,");
        map.put("status", "true");
        return new ResponseEntity<>(map, HttpStatus.CREATED); //201
    }

    @GetMapping //http://localhost:8080/teachers
    public ResponseEntity<List<Teacher>> getAllTeachers(){
        List<Teacher> teachers = teacherService.findAllTeacher();
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> findTeacherByIdWithPathVariable(@PathVariable("id") Long id){
        Teacher teacher = teacherService.getTeacherById(id);
        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }

    @GetMapping("/byLastName")
    public ResponseEntity<List<Teacher>> findTeacherByLastName(@RequestParam String lastName){
        List<Teacher> teacher = teacherService.getTeacherByLastName(lastName);
        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable("id") Long id){
        teacherService.deleteTeacherById(id);
        String message = "Teacher with id " + id + "has been deleted";
        return new ResponseEntity<>(message,HttpStatus.OK);
    }


// without using DTO
    //http://localhost:8080/teachers/1
    @PutMapping("/{id}")
    public ResponseEntity<Map<String,String>> updateTeacher(@PathVariable("id") Long id, @RequestBody Teacher teacher){
        teacherService.updateTeacherById(id, teacher);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Teacher with " + id + "has been updated.");
        map.put("status", "true");
        return ResponseEntity.ok(map); //201
    }

    //Using with DTO
    @PutMapping("/{teacherId}")///http://localhost:8080/teachers/1
    public ResponseEntity<Map<String,String>> updateTeacher(@Valid @PathVariable Long teacherId,
                                                            @RequestBody TeacherDto teacherDto){

        teacherService.updateTeacherByDTO(teacherId,teacherDto);

        Map<String ,String> map = new HashMap<>();
        map.put("message","Teacher with id "+ teacherId+ " has been updated ...");
        map.put("status","true");
        return ResponseEntity.ok(map);
    }

    @GetMapping("/page")//http://localhost:8080/teachers/page?page?page=1&size=3&sort=name&direction/ASC/DESC
    public ResponseEntity<Page<Teacher>> getTeacherByPage(@RequestParam("page") int page,
                                                          @RequestParam("size") int size,
                                                          @RequestParam("sort") String prop,
                                                          @RequestParam("direction")Sort.Direction direction){
        Pageable pageable = PageRequest.of(page,size,Sort.by(direction,prop));
        Page<Teacher>  teacherPage =  teacherService.getTeacherByPage(pageable);
        return  ResponseEntity.ok(teacherPage);

    }

    @GetMapping("/byNameUsingJPQL")//http://localhost:8080/teachers/byNameUsingJPQL?name="MIKAIL"
    public ResponseEntity<List<Teacher>> getTeacherByNmeUsingJPQL(@RequestParam String name){
        List<Teacher>  teachers=  teacherService.getTeacherByNameUsingJPQL(name);
        return new ResponseEntity<>(teachers,HttpStatus.OK);
    }
}
