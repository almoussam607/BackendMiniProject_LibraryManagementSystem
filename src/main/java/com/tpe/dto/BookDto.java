package com.tpe.dto;


import com.tpe.domain.Book;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {


    private Long id;

    @NotNull(message = "Title  can not be null..")
    @NotBlank(message = "Title  can not be white space ")//2
    @Size(min = 4,max= 25,message = "Title  '${validatedValue}' must be between : {min} and {max} ")
    private String title;


    private String author;

    private String publicationDate;

    public BookDto(Book book) {

        this.id=book.getId();
        this.title=book.getTitle();
        this.author=book.getAuthor();
        this.publicationDate=book.getPublicationDate();
    }
}
