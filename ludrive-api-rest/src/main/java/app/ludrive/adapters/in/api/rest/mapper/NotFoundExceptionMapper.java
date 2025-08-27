package app.ludrive.adapters.in.api.rest.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import app.ludrive.core.exception.NotFoundException;

@Provider
@ApplicationScoped
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

    @Inject
    public NotFoundExceptionMapper() {}

    @Override
    public Response toResponse(NotFoundException exception) {
        return Response.status(404).build();
    }
}
