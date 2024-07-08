package com.riwi.LibrosYa.api.dto.response;

import java.time.LocalDate;

import com.riwi.LibrosYa.utils.enums.ReservationStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReservationResponse {
    private Long id;
    private LocalDate reservationDate;
    private ReservationStatus status;
    private Long bookId;
    private Long userId;
}
