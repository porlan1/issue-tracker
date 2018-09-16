package app;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.bundles.assets.ConfiguredAssetsBundle;
import io.dropwizard.jdbi.DBIFactory;
import org.skife.jdbi.v2.DBI;
import app.jdbi.IssueDAO;
import app.resources.api.IssueTrackerResource;

public class IssueTrackerApplication extends Application<IssueTrackerConfiguration> {
    public static void main(String[] args) throws Exception {
        new IssueTrackerApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<IssueTrackerConfiguration> bootstrap) {
        bootstrap.addBundle(new ConfiguredAssetsBundle("/assets/", "/", "index.html"));
    }

    @Override
    public void run(IssueTrackerConfiguration configuration,
                Environment environment) {
        final IssueTrackerResource resource = new IssueTrackerResource();
        environment.jersey().register(resource);
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");
        final IssueDAO dao = jdbi.onDemand(IssueDAO.class);
        environment.jersey().register(new IssueTrackerResource(dao));
    }
}
