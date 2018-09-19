package app.jdbi;
//import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import app.core.mapper.IssueMapper;
import app.core.Issue;
//import org.skife.jdbi.v3.sqlobject.Bind;
//import org.skife.jdbi.v3.sqlobject.customizers.RegisterMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import java.util.List;
import java.sql.Timestamp;

import java.util.Optional;

@RegisterRowMapper(IssueMapper.class)
public interface IssueDAO {
    
    //post insert: project, issue_title, issue_text, created_by, and optional assigned_to and status_text
    /*
    INSERT INTO issue (project, created_on, issue_title,
    issue_text, created_by, assigned_to, status_text) values ( 
        :project,
        now(),
        :issue_title,
        :issue_text,
        :created_by,
        :assigned_to,
        :status_text)
    */
    @SqlQuery("INSERT INTO issue (project, created_on, issue_title, issue_text, created_by, assigned_to, status_text) values (:project, now(), :issue_title, :issue_text, :created_by, :assigned_to, :status_text) RETURNING *")
    Issue create(@Bind("project") String project,
    @Bind("issue_title") Optional<String> issue_title, @Bind("issue_text") Optional<String> issue_text, 
    @Bind("created_by") Optional<String> created_by, @Bind("assigned_to") Optional<String> assigned_to, 
    @Bind("status_text") Optional<String> status_text);

    /*
    //put update: issue_title, issue_text, created_by, and optional assigned_to and status_text
    //with a _id and any fields in the object with a value to object said object. Returned will be 'successfully updated' or 'could not update '+_id
    */
    @SqlUpdate("UPDATE issue SET project = :project, updated_on = now(), open = CASE WHEN :open IS NOT NULL THEN :open ELSE open END, issue_title = CASE WHEN :issue_title IS NOT NULL THEN :issue_title ELSE issue_title END, issue_text = CASE WHEN :issue_text IS NOT NULL THEN :issue_text ELSE issue_text END, created_by = CASE WHEN :created_by IS NOT NULL THEN :created_by ELSE created_by END, assigned_to = CASE WHEN :assigned_to IS NOT NULL THEN :assigned_to ELSE assigned_to END, status_text = CASE WHEN :status_text IS NOT NULL THEN :status_text ELSE status_text END WHERE _id = :_id")
    Boolean update(@Bind("_id") Integer _id, @Bind("project") Optional<String> project, 
    @Bind("open") Optional<Boolean> open,
    @Bind("issue_title") Optional<String> issue_title, @Bind("issue_text") Optional<String> issue_text, 
    @Bind("created_by") Optional<String> created_by, @Bind("assigned_to") Optional<String> assigned_to, 
    @Bind("status_text") Optional<String> status_text);
    
    //I can GET /api/issues/{project} for an array of all issues on that specific project with all the information for each issue as was returned when posted.
    //I can filter my get request by also passing along any field and value in the query(ie. /api/issues/{project}?open=false). I can pass along as many fields/values as I want.
    /*
    SELECT _id, project, 
    CASE WHEN :created_on THEN created_on ELSE NULL END, 
    CASE WHEN :issue_title THEN issue_title ELSE NULL END, 
    CASE WHEN :issue_text THEN issue_text ELSE NULL END, 
    CASE WHEN :created_by THEN created_by ELSE NULL END, 
    CASE WHEN :assigned_to THEN assigned_to ELSE NULL END, 
    CASE WHEN :status_text THEN status_text ELSE NULL END
    FROM issue WHERE TRUE
    AND CASE WHEN :_id THEN _id = :_id ELSE TRUE END
    AND CASE WHEN :created_on THEN created_on = :created_on ELSE TRUE END
    AND CASE WHEN :issue_title THEN issue_title = :issue_title ELSE TRUE END
    AND CASE WHEN :issue_text THEN issue_text = :issue_text ELSE TRUE END
    AND CASE WHEN :created_by THEN created_by = :created_by ELSE TRUE END
    AND CASE WHEN :assigned_to THEN assigned_to = :assigned_to ELSE TRUE END
    AND CASE WHEN :status_text THEN status_text = :status-text ELSE TRUE END
    */
    @SqlQuery("SELECT * FROM issue WHERE TRUE AND CASE WHEN :_id IS NOT NULL THEN _id = :_id ELSE TRUE END AND CASE WHEN :issue_title IS NOT NULL THEN issue_title = :issue_title ELSE TRUE END AND CASE WHEN :issue_text IS NOT NULL THEN issue_text = :issue_text ELSE TRUE END AND CASE WHEN :created_by IS NOT NULL THEN created_by = :created_by ELSE TRUE END AND CASE WHEN :assigned_to IS NOT NULL THEN assigned_to = :assigned_to ELSE TRUE END AND CASE WHEN :status_text IS NOT NULL THEN status_text = :status_text ELSE TRUE END")
    List<Issue> find(@Bind("_id") Optional <Integer> _id, @Bind("project") Optional <String> project, 
    @Bind("open") Optional <Boolean> open, @Bind("issue_title") Optional <String> issue_title,
    @Bind("issue_text") Optional <String> issue_text, @Bind("created_by") Optional<String> created_by,
    @Bind("assigned_to") Optional <String> assigned_to, @Bind("status_text") Optional <String> status_text);
    

    @SqlQuery("SELECT * FROM issue WHERE _id = :_id")
    List<Issue> findById(@Bind("_id") int _id);

    /*
    //I can DELETE /api/issues/{project} with a _id to completely delete an issue. If no _id is sent return '_id error', success: 'deleted '+_id, failed: 'could not delete '+_id.
    */
    @SqlUpdate("DELETE FROM issue WHERE _id = :_id")
    Boolean deleteById(@Bind("_id") int _id);
}
