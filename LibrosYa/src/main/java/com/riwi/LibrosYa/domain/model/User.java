package com.riwi.LibrosYa.domain.model;

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
public class User {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String fullName;
    private UserRole role;
    private List<Reservation> reservations;
    private List<Loan> loans;
}
