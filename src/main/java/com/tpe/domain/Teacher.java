package com.tpe.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be white space")
    @Size(min = 4, max = 25, message = "name '${validatedValue}' must be between {min} and {max}")
    private String name;


    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Email(message = "Provide valid email")
    @Column(nullable = false, length = 35, unique = true)
    private String email;

    private String phoneNumber;

    private LocalDateTime registerDate = LocalDateTime.now();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "teacher_book",
    joinColumns = @JoinColumn(name = "teacher_id"),
    inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> books = new ArrayList<>();
}
