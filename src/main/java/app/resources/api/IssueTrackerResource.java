package app.resources.api;

import app.core.IssueTracker;
import com.codahale.metrics.annotation.Timed;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.owasp.encoder.Encode;
import app.jdbi.IssueDAO;
//import javax.ws.rs.core.Response;

@Path("/convert")
@Produces(MediaType.APPLICATION_JSON)
public class IssueTrackerResource {
    IssueDAO dao;

    public IssueTrackerResource(IssueDAO dao) {
        this.dao = dao;
    }

    public IssueTrackerResource() {
    }

    @GET
    @Timed
    public Map<String, String> issueTracker(@QueryParam("input") String input) throws Exception {
        final String value = String.format(Encode.forHtml(input));
        IssueTracker issueTracker = new IssueTracker(value);
        return issueTracker.convertInput();
    }
    /*@GET
    @Timed
    public Response converter(@QueryParam("input") String input) throws Exception {
        try {
            final String value = String.format(Encode.forHtml(input));
            Converter converter = new Converter(value);
            return Response.ok(converter.convertInput()).type(MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            return Response.ok(e.getMessage()).type(MediaType.TEXT_PLAIN).build();
        }
    }*/
}
