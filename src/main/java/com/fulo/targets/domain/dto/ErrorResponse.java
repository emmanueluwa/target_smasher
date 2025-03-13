package com.fulo.targets.domain.dto;

public record ErrorResponse(
        int status,
        String message,
        String details
) {
}
