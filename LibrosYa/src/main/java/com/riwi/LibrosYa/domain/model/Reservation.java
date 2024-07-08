package com.riwi.LibrosYa.domain.model;

import java.time.LocalDate;

import com.riwi.LibrosYa.utils.enums.ReservationStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Reservation {
    private Long id;
    private LocalDate reservationDate;
    private ReservationStatus status;
    private Book book;
    private User user;
}
