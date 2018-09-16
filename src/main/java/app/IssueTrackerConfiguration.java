package app;

import io.dropwizard.Configuration;
import io.dropwizard.bundles.assets.AssetsBundleConfiguration;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.bundles.assets.AssetsConfiguration;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull; 
import javax.validation.Valid;

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

    public DataSourceFactory getDataSourceFactory() {
      return database;
  }
}
