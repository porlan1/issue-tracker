package app;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.bundles.assets.ConfiguredAssetsBundle;
import io.dropwizard.jdbi3.bundles.JdbiExceptionsBundle;

import io.dropwizard.jdbi3.JdbiFactory;
import org.jdbi.v3.core.Jdbi;
import app.jdbi.IssueDAO;
import app.resources.api.IssueTrackerResource;
import java.net.URISyntaxException;

public class IssueTrackerApplication extends Application<IssueTrackerConfiguration> {
    public static void main(String[] args) throws Exception {
        new IssueTrackerApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<IssueTrackerConfiguration> bootstrap) {
        bootstrap.addBundle(new ConfiguredAssetsBundle("/assets/", "/", "index.html"));
        bootstrap.addBundle(new JdbiExceptionsBundle());
    }

    @Override
    public void run(IssueTrackerConfiguration configuration,
                Environment environment) throws URISyntaxException {
        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");
        final IssueDAO dao = jdbi.onDemand(IssueDAO.class);
        environment.jersey().register(new IssueTrackerResource(dao));
    }
}
