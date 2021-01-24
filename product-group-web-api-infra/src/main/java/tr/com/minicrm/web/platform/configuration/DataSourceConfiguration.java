package tr.com.minicrm.web.platform.configuration;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import liquibase.integration.spring.SpringLiquibase;

@Configuration
@ConditionalOnExpression("'${platform.datasource.databaseType}' == 'mysql' or '${platform.datasource.databaseType}' == 'postgresql'")
public class DataSourceConfiguration {

  @Bean
  @ConfigurationProperties("platform.datasource")
  public HikariDataSource dataSource() {
    return DataSourceBuilder.create().type(HikariDataSource.class).build();
  }

  @Bean
  public SpringLiquibase liquibase(@Value("${platform.datasource.databaseType}") String databaseType,
      DataSource dataSource) {
    SpringLiquibase liquibase = new SpringLiquibase();
    liquibase.setChangeLog("classpath:db/" + databaseType + "/database-change-log.xml");
    liquibase.setDataSource(dataSource);
    return liquibase;
  }
}
