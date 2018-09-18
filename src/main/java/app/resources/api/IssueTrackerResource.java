package app.resources.api;

import app.core.Issue;
import com.codahale.metrics.annotation.Timed;
import java.util.Map;

import javax.ws.rs.*;
/*import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import javax.ws.rs.QueryParam;
import javax.ws.rs.PathParam;*/
import javax.ws.rs.core.MediaType;
import org.owasp.encoder.Encode;
import app.jdbi.IssueDAO;

import java.util.List;
import java.sql.Timestamp;
import java.util.Optional;
//import javax.ws.rs.core.Response;

@Path("/issues/{project}")
@Produces(MediaType.APPLICATION_JSON)
public class IssueTrackerResource {
    IssueDAO dao;

    public IssueTrackerResource(IssueDAO dao) {
        this.dao = dao;
    }
    
    /*@POST
    @Timed
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Map<String, String> createIssue(@PathParam("project") String project,
        @FormParam("accept") String accept) throws Exception {
        final String value = String.format(Encode.forHtml(project));
        IssueTracker issueTracker = new IssueTracker(value);
        return issueTracker.convertInput();
    }*/

    /*@PUT
    @Timed
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Map<String, String> updateIssue(@QueryParam("_id") String _id,
    @QueryParam("created_on") String created_on,
    @QueryParam("updated_on") String updated_on,
    @QueryParam("open") String open,
    @QueryParam("issue_title") String issue_title,
    @QueryParam("issue_text") String issue_text,
    @QueryParam("created_by") String created_by,
    @QueryParam("assigned_to") String assigned_to,
    @QueryParam("status_text") String status_text) throws Exception {
        final String value = String.format(Encode.forHtml(_id));
        IssueTracker issueTracker = new IssueTracker(value);
        return issueTracker.convertInput();
    }*/

    @DELETE
    @Timed
    public List <Issue> deleteIssue(@QueryParam("_id") int _id) throws Exception {
        System.out.println(_id);
        System.out.println(_id);
        System.out.println(_id);
        System.out.println(_id);
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        return dao.deleteById(_id);
    }
    
    /*@GET
    @Timed
    public List<Issue> getIssues(
    @QueryParam("_id") int _id) throws Exception {
        //final String value = String.format(Encode.forHtml(project));
        return dao.findById(_id);
    }*/

    @GET
    @Timed
    public List<Issue> getIssues(@PathParam("project") Optional <String> project,
    @QueryParam("_id") Optional <Integer> _id,
    @QueryParam("created_on") Optional <Timestamp> created_on,
    @QueryParam("updated_on") Optional <Timestamp> updated_on,
    @QueryParam("open") Optional <Boolean> open,
    @QueryParam("issue_title") Optional <String> issue_title,
    @QueryParam("issue_text") Optional <String> issue_text,
    @QueryParam("created_by") Optional <String> created_by,
    @QueryParam("assigned_to") Optional <String> assigned_to,
    @QueryParam("status_text") Optional <String> status_text) throws Exception {
        //final String value = String.format(Encode.forHtml(project));
        return dao.find(_id, project, created_on, updated_on, open, issue_title, issue_text, created_by, assigned_to, status_text);
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
