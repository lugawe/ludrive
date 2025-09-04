package app.ludrive.adapters.in.api.rest.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import app.ludrive.core.exception.AccessDeniedException;

@Provider
@ApplicationScoped
public class AccessDeniedExceptionMapper implements ExceptionMapper<AccessDeniedException> {

    @Inject
    public AccessDeniedExceptionMapper() {}

    @Override
    public Response toResponse(AccessDeniedException exception) {
        return Response.status(403).build();
    }
}
