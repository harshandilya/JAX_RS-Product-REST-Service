package com.harsh.productapi.exception;

import java.util.List;

import com.fasterxml.jackson.core.JacksonException;
import com.harsh.productapi.model.ProblemResponse;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class JsonExceptionMapper implements ExceptionMapper<JacksonException> {

    @Context
    private UriInfo uriInfo;

    @Override
    public Response toResponse(JacksonException exception) {
        ProblemResponse problem = ProblemResponse.of(
                "Bad Request",
                400,
                "Malformed or type-invalid JSON",
                uriInfo.getRequestUri().getPath(),
                List.of(
                        "The request body is not valid JSON. "
                        + "Check commas, quotation marks, field names, and value types."
                )
        );

        return Response.status(Response.Status.BAD_REQUEST)
                .type("application/problem+json")
                .entity(problem)
                .build();
    }
}