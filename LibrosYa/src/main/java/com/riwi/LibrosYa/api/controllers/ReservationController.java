package com.riwi.LibrosYa.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.LibrosYa.api.dto.errors.ErrorResponse;
import com.riwi.LibrosYa.api.dto.request.ReservationRequest;
import com.riwi.LibrosYa.api.dto.response.ReservationResponse;
import com.riwi.LibrosYa.infrastructure.abstracts.IReservationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/reservations")
@AllArgsConstructor
public class ReservationController {

  @Autowired
  private final IReservationService iReservationService;

  @Operation(summary = "Show the reservations by pagination")
  @ApiResponse(responseCode = "400", description = "When the params send are invalid", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
  @GetMapping
  public ResponseEntity<Page<ReservationResponse>> showAll(@RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "3") int size) {

    return ResponseEntity.ok(this.iReservationService.getAll(size, page - 1));
  }

  @Operation(summary = "Show the reservation according to the id given")
  @ApiResponse(responseCode = "400", description = "When the id given is not found", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
  @GetMapping(path = "/{id}")
  public ResponseEntity<ReservationResponse> showReservation(@PathVariable Long id) {

    return ResponseEntity.ok(this.iReservationService.getById(id));
  }

  @Operation(summary = "Create a reservation with the information given")
  @ApiResponse(responseCode = "400", description = "When the information given is incorrect", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
  @PostMapping(path = "/add")
  public ResponseEntity<ReservationResponse> addReservation(
      @Validated @RequestBody ReservationRequest ReservationRequest) {

    return ResponseEntity.ok(this.iReservationService.create(ReservationRequest));
  }

  @Operation(summary = "Update a reservation according to the id summoned")
  @ApiResponse(responseCode = "400", description = "When the id given is not found or the information is incorrect", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
  @PutMapping(path = "/update/{id}")
  public ResponseEntity<ReservationResponse> updateReservation(
      @Validated @RequestBody ReservationRequest ReservationRequest,
      @PathVariable Long id) {

    return ResponseEntity.ok(this.iReservationService.update(id, ReservationRequest));
  }

  @Operation(summary = "Delete a reservation according to the id given")
  @ApiResponse(responseCode = "400", description = "When the id given is not found", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
    this.iReservationService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @Operation(summary = "show the reservations of the book given")
  @ApiResponse(responseCode = "400", description = "When the id given is not found", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
  @GetMapping(path = "/book/{id}")
  public ResponseEntity<List<ReservationResponse>> showReservationsByUserId(@PathVariable Long id) {

    return ResponseEntity.ok(this.iReservationService.getReservationsByBookId(id));
  }
}
