package com.riwi.LibrosYa.domain.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Book {
    private Long id;
    private String title;
    private String author;
    private int publicationYear;
    private String genre;
    private List<Reservation> reservations;
    private List<Loan> loans;
    private String isbn;
}
