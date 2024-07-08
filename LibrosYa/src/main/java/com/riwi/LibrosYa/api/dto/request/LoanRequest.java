package com.riwi.LibrosYa.api.dto.request;

import java.time.LocalDate;

import com.riwi.LibrosYa.utils.enums.LoanStatus;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoanRequest {

    @Future(message = "Date of return cannot be in the past")
    private LocalDate returnDate;
    
    @NotNull(message = "The status is required")
    private LoanStatus status;

    @NotNull(message = "The book to be loaned is required")
    private Long bookId;

    @NotNull(message = "The user that will do the loan is required")
    private Long userId;
}
