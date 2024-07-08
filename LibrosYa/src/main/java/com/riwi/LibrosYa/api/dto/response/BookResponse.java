package com.riwi.LibrosYa.api.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BookResponse {
    
    private Long id;
    private String title;
    private String author;
    private String genre;
    private String isbn;
    private List<LoanResponse> loans;
    private List<ReservationResponse> reservations;
}
