package com.riwi.LibrosYa.api.dto.response;

import java.util.List;

import com.riwi.LibrosYa.utils.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserResponse {
    private Long id;
    private String email;
    private String fullName;
    private UserRole role;
    private List<ReservationResponse> reservations;
    private List <LoanResponse> loans;
}
