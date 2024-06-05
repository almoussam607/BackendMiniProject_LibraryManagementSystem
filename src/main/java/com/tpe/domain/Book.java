package com.tpe.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotNull(message = "Title cannot be Null")
    @NotBlank(message = "Title cannot be white space")
    @Size(min =3, max = 35, message = "Title '${validatedValue}' mus be between {min} and {max}")
    private String title;

    private String author;

    @Column(nullable = false)
    private String publicationDate;

    @ManyToMany(mappedBy = "books", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("books")
    private List<Teacher> teachers = new ArrayList<>();
}
