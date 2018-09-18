package app.core.mapper;

import app.core.Issue;
import org.jdbi.v3.core.statement.StatementContext;
import org.jdbi.v3.core.mapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IssueMapper implements RowMapper<Issue>
{
    public Issue map(ResultSet resultSet, StatementContext statementContext) throws SQLException
    {
        return new Issue(resultSet.getInt("_ID"), resultSet.getString("PROJECT"),
        resultSet.getTimestamp("CREATED_ON"), resultSet.getTimestamp("UPDATED_ON"),
        resultSet.getBoolean("OPEN"), resultSet.getString("ISSUE_TITLE"),
        resultSet.getString("ISSUE_TEXT"), resultSet.getString("CREATED_BY"),
        resultSet.getString("ASSIGNED_TO"), resultSet.getString("STATUS_TEXT"));
    }
}
