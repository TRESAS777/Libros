package com.riwi.LibrosYa.api.dto.request;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookRequest {

    @NotBlank(message = "The title is required")
    @Length(min = 3, max = 100, message = "Invalid lenght for the title")
    private String title;

    @NotBlank(message = "The author is required")
    @Length(min = 3, max = 100, message = "Invalid lenght for the author name")
    private String author;

    @Past(message = "The year of publication can not be in the future")
    private int publicationYear;

    @NotBlank(message = "The genre is required")
    @Length(min = 3, max = 50, message = "Invalid lenght for the  genre")
    private String genre;

    @NotBlank(message = "The isbn is required")
    @Length(min = 3, max = 20, message = "Invalid lenght for the isbn")
    private String isbn;
}
