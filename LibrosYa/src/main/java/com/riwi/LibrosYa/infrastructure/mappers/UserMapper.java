package com.riwi.LibrosYa.infrastructure.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.riwi.LibrosYa.api.dto.request.UserRequest;
import com.riwi.LibrosYa.api.dto.response.LoanResponse;
import com.riwi.LibrosYa.api.dto.response.ReservationResponse;
import com.riwi.LibrosYa.api.dto.response.UserResponse;
import com.riwi.LibrosYa.domain.model.Loan;
import com.riwi.LibrosYa.domain.model.Reservation;
import com.riwi.LibrosYa.domain.model.User;
import com.riwi.LibrosYa.infrastructure.persistence.UserEntity;


@Mapper(componentModel = "spring")
public  interface UserMapper {
    

    public UserResponse userToUserResponse(User user);

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "loans",ignore = true)
    @Mapping(target = "reservations",ignore = true)
    public User userRequestToUser(UserRequest userRequest);

    public UserEntity userToUserEntity(User user);

    public User userEntityToUser(UserEntity userEntity);

    public List<LoanResponse> loanToLoanResponse(List<Loan> loans);
    public List<ReservationResponse> reservationToReservationResponse(List<Reservation> reservations);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "book.id", target = "bookId")
    public LoanResponse loanToLoanResponse(Loan loan);
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "book.id", target = "bookId")
    public ReservationResponse reservationToReservationResponse(Reservation reservation);
}
