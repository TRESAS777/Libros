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
import com.riwi.LibrosYa.api.dto.request.LoanRequest;
import com.riwi.LibrosYa.api.dto.response.LoanResponse;
import com.riwi.LibrosYa.infrastructure.abstracts.ILoanService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/loans")
@AllArgsConstructor
public class LoanController {

  @Autowired
  private final ILoanService iLoanService;

  @Operation(summary = "Show the loans by pagination")
  @ApiResponse(responseCode = "400", description = "When the params send are invalid", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
  @GetMapping
  public ResponseEntity<Page<LoanResponse>> showAll(@RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "3") int size) {

    return ResponseEntity.ok(this.iLoanService.getAll(size, page - 1));
  }

  @Operation(summary = "Show the loan according to the id given")
  @ApiResponse(responseCode = "400", description = "When the id given is not found", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
  @GetMapping(path = "/{id}")
  public ResponseEntity<LoanResponse> showLoan(@PathVariable Long id) {

    return ResponseEntity.ok(this.iLoanService.getById(id));
  }

  @Operation(summary = "Create a loan with the information given")
  @ApiResponse(responseCode = "400", description = "When the information given is incorrect", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
  @PostMapping(path = "/add")
  public ResponseEntity<LoanResponse> addLoan(@RequestBody @Validated LoanRequest LoanRequest) {

    return ResponseEntity.ok(this.iLoanService.create(LoanRequest));
  }

  @Operation(summary = "Update a loan according to the id summoned")
  @ApiResponse(responseCode = "400", description = "When the id given is not found or the information is incorrect", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
  @PutMapping(path = "/update/{id}")
  public ResponseEntity<LoanResponse> updateLoan(@RequestBody @Validated LoanRequest LoanRequest,
      @PathVariable Long id) {

    return ResponseEntity.ok(this.iLoanService.update(id, LoanRequest));
  }

  @Operation(summary = "Delete a loan according to the id given")
  @ApiResponse(responseCode = "400", description = "When the id given is not found", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Void> deleteLoan(@PathVariable Long id) {
    this.iLoanService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @Operation(summary = "show the loans of the book given")
  @ApiResponse(responseCode = "400", description = "When the id given is not found", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
  @GetMapping(path = "/book/{id}")
  public ResponseEntity<List<LoanResponse>> showLoansByBookId(@PathVariable Long id) {

    return ResponseEntity.ok(this.iLoanService.getLoansByBookId(id));
  }

  @Operation(summary = "show the loans of the user given")
  @ApiResponse(responseCode = "400", description = "When the id given is not found", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
  @GetMapping(path = "/user/{id}")
  public ResponseEntity<List<LoanResponse>> showLoansByUserId(@PathVariable Long id) {

    return ResponseEntity.ok(this.iLoanService.getLoansByUserId(id));
  }
}
