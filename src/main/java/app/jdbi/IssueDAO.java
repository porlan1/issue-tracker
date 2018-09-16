package app.jdbi;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.Bind;

public interface IssueDAO {
    private final String createQuery;
    /*
    CREATE TABLE issue (_id integer primary key,
    project text NOT NULL UNIQUE,
    created_on TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_on TIMESTAMP WITH TIME ZONE,
    open BOOLEAN NOT NULL EFAULT TRUE,
    issue_title text NOT NULL DEFAULT '',
    issue_text text NOT NULL DEFAULT '',
    created_by text NOT NULL,
    assigned_to text,
    status_text text)

    */
    @SqlUpdate("CREATE TABLE issue (_id integer primary key,
    project text NOT NULL UNIQUE,
    created_on TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_on TIMESTAMP WITH TIME ZONE,
    open BOOLEAN NOT NULL EFAULT TRUE,
    issue_title text NOT NULL DEFAULT '',
    issue_text text NOT NULL DEFAULT '',
    created_by text NOT NULL,
    assigned_to text,
    status_text text)
    ")
    void createSomethingTable();
    
    //post insert: project, issue_title, issue_text, created_by, and optional assigned_to and status_text
    @SqlUpdate("INSERT INTO issue (id, name) values (:id, :name)")
    void insert(@Bind("id") int id, @Bind("name") String name);
    
    //put update: issue_title, issue_text, created_by, and optional assigned_to and status_text
    //with a _id and any fields in the object with a value to object said object. Returned will be 'successfully updated' or 'could not update '+_id
    @SqlUpdate("INSERT INTO issue (id, name) values (:id, :name)")
    void insert(@Bind("id") int id, @Bind("name") String name);

    //I can GET /api/issues/{projectname} for an array of all issues on that specific project with all the information for each issue as was returned when posted.
    //I can filter my get request by also passing along any field and value in the query(ie. /api/issues/{project}?open=false). I can pass along as many fields/values as I want.
    @SqlQuery("SELECT name FROM something WHERE id = :id")
    String findNameById(@Bind("id") int id);

    //I can DELETE /api/issues/{projectname} with a _id to completely delete an issue. If no _id is sent return '_id error', success: 'deleted '+_id, failed: 'could not delete '+_id.
    @SqlQuery("DELETE FROM issue WHERE id = :id")
    String deleteById(@Bind("id") int id);
}
