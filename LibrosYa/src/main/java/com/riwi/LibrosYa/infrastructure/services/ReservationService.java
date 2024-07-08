package com.riwi.LibrosYa.infrastructure.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.riwi.LibrosYa.api.dto.request.ReservationRequest;
import com.riwi.LibrosYa.api.dto.response.ReservationResponse;
import com.riwi.LibrosYa.domain.model.Reservation;
import com.riwi.LibrosYa.domain.repositories.BookRepository;
import com.riwi.LibrosYa.domain.repositories.ReservationRepository;
import com.riwi.LibrosYa.domain.repositories.UserRepository;
import com.riwi.LibrosYa.infrastructure.abstracts.IReservationService;
import com.riwi.LibrosYa.infrastructure.mappers.BookMapper;
import com.riwi.LibrosYa.infrastructure.mappers.ReservationMapper;
import com.riwi.LibrosYa.infrastructure.mappers.UserMapper;
import com.riwi.LibrosYa.infrastructure.persistence.ReservationEntity;
import com.riwi.LibrosYa.utils.exceptions.IdNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReservationService implements IReservationService {

    @Autowired
    private final ReservationRepository reservationRepository;

    @Autowired
    private final ReservationMapper reservationMapper;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final BookRepository bookRepository;

    @Autowired
    private final UserMapper userMapper;

    @Autowired
    private final BookMapper bookMapper;

    @Override
    public Page<ReservationResponse> getAll(int size, int page) {
        if (page < 0) {
            page = 0;
        }
        Pageable pageable = PageRequest.of(page, size);

        return this.reservationRepository.findAll(pageable).map(reservation -> reservationMapper
                .reservationToReservationResponse(reservationMapper.reservationEntityToReservation(reservation)));
    }

    @Override
    public ReservationResponse getById(Long id) {
        Reservation reservation = reservationMapper.reservationEntityToReservation(findReservationEntity(id));

        return reservationMapper.reservationToReservationResponse(reservation);
    }

    private ReservationEntity findReservationEntity(Long id) {

        return this.reservationRepository.findById(id).orElseThrow(() -> new IdNotFoundException("reservations"));
    }

    @Override
    public ReservationResponse create(ReservationRequest request) {
        Reservation reservation = reservationMapper.reservationRequestToReservation(request);

        reservation.setUser(userMapper.userEntityToUser(
                this.userRepository.findById(request.getUserId()).orElseThrow(() -> new IdNotFoundException("users"))));

        reservation.setBook(bookMapper.bookEntityToBook(
                this.bookRepository.findById(request.getBookId()).orElseThrow(() -> new IdNotFoundException("books"))));

        return reservationMapper.reservationToReservationResponse(reservationMapper.reservationEntityToReservation(
                this.reservationRepository.save(reservationMapper.reservationToReservationEntity(reservation))));
    }

    @Override
    public ReservationResponse update(Long id, ReservationRequest request) {
        Reservation reservation = reservationMapper.reservationEntityToReservation(findReservationEntity(id));
        reservation = reservationMapper.reservationRequestToReservation(request);

        reservation.setUser(userMapper.userEntityToUser(
                this.userRepository.findById(request.getUserId()).orElseThrow(() -> new IdNotFoundException("users"))));

        reservation.setBook(bookMapper.bookEntityToBook(
                this.bookRepository.findById(request.getBookId()).orElseThrow(() -> new IdNotFoundException("books"))));
        return reservationMapper.reservationToReservationResponse(reservationMapper.reservationEntityToReservation(
                this.reservationRepository.save(reservationMapper.reservationToReservationEntity(reservation))));
    }

    @Override
    public void delete(Long id) {
        ReservationEntity reservationEntity = findReservationEntity(id);

        this.reservationRepository.delete(reservationEntity);
    }

    @Override
    public List<ReservationResponse> getReservationsByBookId(Long id) {

        return this.reservationRepository.findByBookId(id).stream()
                .map(reservation -> reservationMapper.reservationToReservationResponse(
                        reservationMapper.reservationEntityToReservation(reservation)))
                .toList();
    }

}
