package tr.com.minicrm.web.platform.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import tr.com.minicrm.web.productgroup.configuration.ProductGroupBusinessServiceConfiguration;

@Configuration
@ComponentScan(basePackages = "tr.com.minicrm")
@Import({ProductGroupBusinessServiceConfiguration.class})
@EnableAutoConfiguration(exclude = {MongoReactiveAutoConfiguration.class, MongoAutoConfiguration.class,
    MongoRepositoriesAutoConfiguration.class, MongoDataAutoConfiguration.class, DataSourceAutoConfiguration.class,
    HibernateJpaAutoConfiguration.class, JdbcTemplateAutoConfiguration.class,
    DataSourceTransactionManagerAutoConfiguration.class})
public class MainConfiguration {


}
