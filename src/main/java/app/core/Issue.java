package app.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Timestamp;

public class Issue {

    @JsonProperty
    private Integer _id;

    @JsonProperty
    private String project;

    @JsonProperty
    private Timestamp created_on;

    @JsonProperty
    private Timestamp updated_on;

    @JsonProperty
    private Boolean open;

    @JsonProperty
    private String issue_title;

    @JsonProperty
    private String issue_text;

    @JsonProperty
    private String created_by;

    @JsonProperty
    private String assinged_to;

    @JsonProperty
    private String status_text;

    public Issue() {
        // Jackson deserialization
    }

    public Issue(int _id, String project, Timestamp created_on,
    Timestamp updated_on, Boolean open, String issue_title, 
    String issue_text, String created_by, String assigned_to, String status_text) {
        this._id = _id;
        this.project = project;
        this.created_on = created_on;
        this.updated_on = updated_on;
        this.open = open;
        this.issue_title = issue_title;
        this.issue_text = issue_text;
        this.created_by = created_by;
        this.assinged_to = assigned_to;
        this.status_text = status_text;
    }
}
