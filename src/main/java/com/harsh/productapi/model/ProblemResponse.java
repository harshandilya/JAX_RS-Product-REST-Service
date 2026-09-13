package com.harsh.productapi.model;

import java.time.Instant;
import java.util.List;

public record ProblemResponse(
        String type,
        String title,
        int status,
        String detail,
        String instance,
        String timestamp,
        List<String> errors
) {

    public static ProblemResponse of(
            String title,
            int status,
            String detail,
            String instance,
            List<String> errors
    ) {
        return new ProblemResponse(
                "about:blank",
                title,
                status,
                detail,
                instance,
                Instant.now().toString(),
                errors == null ? List.of() : errors
        );
    }
}