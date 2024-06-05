package com.tpe.repository;


import com.tpe.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {


    Teacher findByEmail(String email);

    List<Teacher> findByLastName(String lastName);

    @Query("SELECT t FROM Teacher t WHERE t.name=:name")
    List<Teacher> findByNameUsingJPQL(@Param("name") String name);
}
