package com.amadeus.flightsearchengine.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@Tag(name = "JWT", description = "Authentication")
public interface JWTController
{
    @GetMapping("/jwt")
    @Operation(summary = "Generate JWT token", description = "Generate a random JWT token. Simple generation, doesn't need username, password, etc. It was just implemented for demonstration purposes only.")
    @ApiResponse(responseCode = "200", description = "Successful generation of JWT token", content = {
            @Content(schema = @Schema(implementation = String.class), examples = @ExampleObject(name = "JWTExample", value = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"))})
    ResponseEntity<String> generateJwtToken();
}
