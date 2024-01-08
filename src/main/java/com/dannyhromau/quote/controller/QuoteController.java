package com.dannyhromau.quote.controller;

import com.dannyhromau.quote.api.dto.QuoteDto;
import com.dannyhromau.quote.core.base.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/quote")
@Tag(name = "Quote service", description = "Quote controller")
@ApiResponse(responseCode = "200", description = "Successful operation")
@ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
@ApiResponse(responseCode = "404", description = "Not found")
@ApiResponse(responseCode = "401", description = "Unauthorized")
@ApiResponse(responseCode = "503", description = "Service unavailable")
public interface QuoteController extends BaseController<QuoteDto> {
    @Override
    @GetMapping("/{id}")
    @Operation(description = "Getting quote by id")
    ResponseEntity<QuoteDto> getById(@PathVariable @NonNull UUID id);

    @Override
    @GetMapping("/all")
    @Operation(description = "Getting all quotes")
    ResponseEntity<List<QuoteDto>> getAll(Pageable page);

    @Override
    @PostMapping(value = "/create")
    @Operation(description = "Creating quote")
    ResponseEntity<QuoteDto> create(@RequestBody @NonNull QuoteDto dto);

    @Override
    @PutMapping(value = "/update")
    @Operation(description = "Updating quote")
    ResponseEntity<QuoteDto> update(@RequestBody @NonNull QuoteDto dto);

    @Override
    @DeleteMapping(value = "/{id}")
    @Operation(description = "Deleting comment")
    ResponseEntity<UUID> deleteById(@PathVariable @NonNull UUID id);
}
