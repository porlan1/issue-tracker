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
    
    @POST
    @Timed
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Issue createIssue(@PathParam("project") String project,
    @QueryParam("issue_title") Optional <String> issue_title,
    @QueryParam("issue_text") Optional<String> issue_text,
    @QueryParam("created_by") Optional<String> created_by,
    @QueryParam("assigned_to") Optional<String> assigned_to,
    @QueryParam("status_text") Optional<String> status_text) throws Exception {
        return dao.create(project,
        issue_title, issue_text, 
        created_by, assigned_to, 
        status_text);
    }

    @PUT
    @Timed
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String updateIssue(@QueryParam("_id") Integer _id,
    @PathParam("project") Optional<String> project,
    @QueryParam("open") Optional <Boolean> open,
    @QueryParam("issue_title") Optional<String> issue_title,
    @QueryParam("issue_text") Optional<String> issue_text,
    @QueryParam("created_by") Optional<String> created_by,
    @QueryParam("assigned_to") Optional <String> assigned_to,
    @QueryParam("status_text") Optional <String> status_text) throws Exception {
        if (_id == null || 
        (project == null && open == null && 
        issue_title == null && issue_text == null && 
        created_by == null && assigned_to == null && 
        status_text == null)) {
            return "no updated field sent";
        }
        if (dao.update(_id, project, open,
        issue_title, issue_text, 
        created_by, assigned_to, 
        status_text)) {
            return "successfully updated";
        }
        return "could not update " + _id.toString();  
    }

    @DELETE
    @Timed
    public String deleteIssue(@QueryParam("_id") Integer _id) throws Exception {
        if (_id == null) {
            return "_id error";
        }
        if (dao.deleteById(_id)) {
            return "deleted " + _id.toString();
        }
        return "could not delete " + _id.toString();
    }

    @GET
    @Timed
    public List<Issue> getIssues(@PathParam("project") Optional <String> project,
    @QueryParam("_id") Optional <Integer> _id,
    @QueryParam("open") Optional <Boolean> open,
    @QueryParam("issue_title") Optional <String> issue_title,
    @QueryParam("issue_text") Optional <String> issue_text,
    @QueryParam("created_by") Optional <String> created_by,
    @QueryParam("assigned_to") Optional <String> assigned_to,
    @QueryParam("status_text") Optional <String> status_text) throws Exception {
        return dao.find(_id, project, open, issue_title, issue_text, created_by, assigned_to, status_text);
    }

}
