package com.riwi.LibrosYa.api.controllers;

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
import com.riwi.LibrosYa.api.dto.request.BookRequest;
import com.riwi.LibrosYa.api.dto.response.BookResponse;
import com.riwi.LibrosYa.infrastructure.abstracts.IBookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/books")
@AllArgsConstructor
public class BookController {
    
    @Autowired
    private final IBookService iBookService;

    @Operation(summary = "Show the books by pagination")
  @ApiResponse(responseCode = "400", description = "When the params send are invalid", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
  @GetMapping
  public ResponseEntity<Page<BookResponse>> showAll(@RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "3") int size) {

    return ResponseEntity.ok(this.iBookService.getAll(size, page - 1));
  }

  @Operation(summary = "Show the book according to the id given")
  @ApiResponse(responseCode = "400", description = "When the id given is not found", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
  @GetMapping(path = "/{id}")
  public ResponseEntity<BookResponse> showbook(@PathVariable Long id) {

    return ResponseEntity.ok(this.iBookService.getById(id));
  }

  @Operation(summary = "Create a book with the information given")
  @ApiResponse(responseCode = "400", description = "When the information given is incorrect", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
  @PostMapping(path = "/add")
  public ResponseEntity<BookResponse> addBook(@RequestBody @Validated BookRequest bookRequest) {

    return ResponseEntity.ok(this.iBookService.create(bookRequest));
  }

  @Operation(summary = "Update a book according to the id summoned")
  @ApiResponse(responseCode = "400", description = "When the id given is not found or the information is incorrect", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
  @PutMapping(path = "/update/{id}")
  public ResponseEntity<BookResponse> updateBook(@RequestBody @Validated BookRequest bookRequest,
      @PathVariable Long id) {

    return ResponseEntity.ok(this.iBookService.update(id, bookRequest));
  }

  @Operation(summary = "Delete a book according to the id given")
  @ApiResponse(responseCode = "400", description = "When the id given is not found", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) })
  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
    this.iBookService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
