package app;

import io.dropwizard.Configuration;
import io.dropwizard.bundles.assets.AssetsBundleConfiguration;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.bundles.assets.AssetsConfiguration;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull; 
import javax.validation.Valid;

import java.net.URI;
import java.net.URISyntaxException;

public class IssueTrackerConfiguration extends Configuration implements AssetsBundleConfiguration {
    
    @JsonProperty
    private final AssetsConfiguration assets = AssetsConfiguration.builder().build();

    @Valid
    @NotNull
    @JsonProperty
    private DataSourceFactory database = new DataSourceFactory();

    @Override
    public AssetsConfiguration getAssetsConfiguration() {
      return assets;
    }

  @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() throws URISyntaxException {
      URI dbUri = new URI(System.getenv("DATABASE_URL"));
      final String user = dbUri.getUserInfo().split(":")[0];
      final String password = dbUri.getUserInfo().split(":")[1];
			final String url = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath()
					+ "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
          DataSourceFactory dsf = new DataSourceFactory();
      dsf.setUser(user);
      dsf.setPassword(password);
      dsf.setUrl(url);
      dsf.setDriverClass("org.postgresql.Driver");
      return dsf;
    }

    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory dataSourceFactory) {
        this.database = dataSourceFactory;
    }
}
