package com.riwi.LibrosYa.infrastructure.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.riwi.LibrosYa.api.dto.request.ReservationRequest;
import com.riwi.LibrosYa.api.dto.response.ReservationResponse;
import com.riwi.LibrosYa.domain.model.Reservation;
import com.riwi.LibrosYa.infrastructure.persistence.ReservationEntity;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "book.id", target = "bookId")
    public ReservationResponse reservationToReservationResponse(Reservation reservation);

    public ReservationEntity reservationToReservationEntity(Reservation reservation);

    public Reservation reservationEntityToReservation(ReservationEntity reservationEntity);

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "book",ignore = true)
    @Mapping(target = "reservationDate",ignore = true)
    @Mapping(target = "user",ignore = true)
    public Reservation reservationRequestToReservation(ReservationRequest reservationRequest);
}
