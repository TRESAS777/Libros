package com.riwi.LibrosYa.api.dto.request;

import com.riwi.LibrosYa.utils.enums.ReservationStatus;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ReservationRequest {
    
    @NotNull(message = "The status is required")
    private ReservationStatus status;

    @NotNull(message = "The book to reserve is required")
    private Long bookId;

    @NotNull(message = "The user tha will reserve the book is required")
    private Long userId;
}
