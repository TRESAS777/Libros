package com.riwi.LibrosYa.api.dto.response;

import java.time.LocalDate;

import com.riwi.LibrosYa.utils.enums.LoanStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoanResponse {
    private Long id;
    private LocalDate loanDate;
    private LocalDate returnDate;
    private LoanStatus status;
    private Long bookId;
    private Long userId;
}
