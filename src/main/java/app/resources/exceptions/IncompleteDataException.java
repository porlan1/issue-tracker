package app.resources.exceptions;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class IncompleteDataException extends WebApplicationException {

    public IncompleteDataException(String message) {
      super(Response.status(Status.NOT_ACCEPTABLE).
      entity(message).type("text/plain").build());
    }
}