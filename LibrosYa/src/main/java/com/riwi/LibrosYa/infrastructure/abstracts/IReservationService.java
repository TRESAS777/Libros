package com.riwi.LibrosYa.infrastructure.abstracts;

import java.util.List;

import com.riwi.LibrosYa.api.dto.request.ReservationRequest;
import com.riwi.LibrosYa.api.dto.response.ReservationResponse;

public interface IReservationService extends BaseService<ReservationRequest, ReservationResponse, Long> {

  public List<ReservationResponse> getReservationsByBookId(Long id);
}
