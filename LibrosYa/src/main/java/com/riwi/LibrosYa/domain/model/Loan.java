    package com.riwi.LibrosYa.domain.model;

import java.time.LocalDate;

import com.riwi.LibrosYa.utils.enums.LoanStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Loan {
    private Long id;
    private LocalDate loanDate;
    private LocalDate returnDate;
    private LoanStatus status;
    private Book book;
    private User user;
}
