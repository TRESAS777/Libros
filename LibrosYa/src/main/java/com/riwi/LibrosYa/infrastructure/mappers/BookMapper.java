package com.riwi.LibrosYa.infrastructure.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.riwi.LibrosYa.api.dto.request.BookRequest;
import com.riwi.LibrosYa.api.dto.response.BookResponse;
import com.riwi.LibrosYa.api.dto.response.LoanResponse;
import com.riwi.LibrosYa.api.dto.response.ReservationResponse;
import com.riwi.LibrosYa.domain.model.Book;
import com.riwi.LibrosYa.domain.model.Loan;
import com.riwi.LibrosYa.domain.model.Reservation;
import com.riwi.LibrosYa.infrastructure.persistence.BookEntity;


@Mapper(componentModel = "spring")
public interface BookMapper {
    
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "loans",ignore = true)
    @Mapping(target = "reservations",ignore = true)
    public BookResponse bookToBookResponse(Book book);

    public BookEntity bookToBookEntity(Book book);

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "loans",ignore = true)
    @Mapping(target = "reservations",ignore = true)
    public Book bookRequestToBook(BookRequest bookRequest);

    public Book bookEntityToBook(BookEntity bookEntity);

    public List<LoanResponse> loanToLoanResponse(List<Loan> loans);
    public List<ReservationResponse> reservationToReservationResponse(List<Reservation> reservations);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "book.id", target = "bookId")
    public LoanResponse loanToLoanResponse(Loan loan);
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "book.id", target = "bookId")
    public ReservationResponse reservationToReservationResponse(Reservation reservation);
}
