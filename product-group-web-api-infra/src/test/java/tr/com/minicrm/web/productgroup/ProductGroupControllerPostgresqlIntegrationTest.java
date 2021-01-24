package tr.com.minicrm.web.productgroup;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import tips.smallapps.web.generated.productgroup.model.FindProductGroupQuery;
import tips.smallapps.web.generated.productgroup.model.FindProductGroupQueryOperationResponse;
import tips.smallapps.web.generated.productgroup.model.NewProductGroup;
import tips.smallapps.web.generated.productgroup.model.ProductGroup;

@SpringBootTest(
    properties = {"spring.main.allow-bean-definition-overriding=true", "platform.datasource.databaseType=postgresql"})
@AutoConfigureMockMvc
public class ProductGroupControllerPostgresqlIntegrationTest {

  private static GenericContainer container;
  private static DataSource dataSource;
  private static DSLContext context;

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void testWhenNewProductGroupNameProvidedThanProductGroupDataServiceShouldBeCalled() throws Exception {
    NewProductGroup group = new NewProductGroup().name("demo");
    this.mockMvc
        .perform(post("/product-group").accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(group)))
        .andDo(print()).andExpect(status().isOk());
  }

  @Test
  public void testWhenDuplicateProductGroupNameProvidedThanItShouldThrowException() throws Exception {
    NewProductGroup group = new NewProductGroup().name("demo2");
    this.mockMvc
        .perform(post("/product-group").accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(group)))
        .andDo(print()).andExpect(status().isOk());
    this.mockMvc
        .perform(post("/product-group").accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(group)))
        .andDo(print()).andExpect(status().isBadRequest());
  }

  @Test
  public void testWhenProductGroupNameQueriedThanItShouldReturnResult() throws Exception {
    NewProductGroup group = new NewProductGroup().name("demo3");
    this.mockMvc
        .perform(post("/product-group").accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(group)))
        .andDo(print()).andExpect(status().isOk());
    FindProductGroupQuery query = new FindProductGroupQuery().name(group.getName());
    this.mockMvc
        .perform(post("/product-group/find-by-name").accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(query)))
        .andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.body.name").value(group.getName()));
  }

  @Test
  public void testWhenUnknownProductGroupNameQueriedThanItShouldThrowException() throws Exception {
    NewProductGroup group = new NewProductGroup().name("demo-1");
    FindProductGroupQuery query = new FindProductGroupQuery().name(group.getName());
    this.mockMvc
        .perform(post("/product-group/find-by-name").accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(query)))
        .andDo(print()).andExpect(status().isNotFound());
  }

  @Test
  public void testWhenProductGroupNameUpdatedThanProductGroupDataServiceShouldBeCalled() throws Exception {
    NewProductGroup group = new NewProductGroup().name("demo 4");
    this.mockMvc
        .perform(post("/product-group").accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(group)))
        .andDo(print()).andExpect(status().isOk());
    FindProductGroupQuery query = new FindProductGroupQuery().name(group.getName());
    String content = this.mockMvc
        .perform(post("/product-group/find-by-name").accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(query)))
        .andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

    FindProductGroupQueryOperationResponse response = asObject(content, FindProductGroupQueryOperationResponse.class);
    Integer version = response.getBody().getVersion();
    Long id = response.getBody().getId();
    ProductGroup pg = new ProductGroup().id(id).version(version).name("demo 4.1");
    this.mockMvc
        .perform(put("/product-group").accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(pg)))
        .andDo(print()).andExpect(status().isOk());

    FindProductGroupQuery queryAgain = new FindProductGroupQuery().name(pg.getName());
    this.mockMvc
        .perform(post("/product-group/find-by-name").accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(queryAgain)))
        .andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.body.name").value(pg.getName()))
        .andExpect(jsonPath("$.body.version").value(1));

  }

  @TestConfiguration
  @ConditionalOnProperty(value = "platform.datasource.databaseType", havingValue = "postgresql")
  @EnableAutoConfiguration(exclude = {MongoReactiveAutoConfiguration.class, MongoAutoConfiguration.class,
      MongoRepositoriesAutoConfiguration.class, MongoDataAutoConfiguration.class, DataSourceAutoConfiguration.class,
      HibernateJpaAutoConfiguration.class, JdbcTemplateAutoConfiguration.class,
      DataSourceTransactionManagerAutoConfiguration.class})
  static class ProductGroupInfraDataPostgreSqlConfiguration {

    @Bean
    public DSLContext dslContext() {
      return context;
    }

    @Bean
    public DataSource dataSource() {
      return dataSource;
    }

  }

  public static String asJsonString(final Object obj) {
    try {
      final ObjectMapper mapper = new ObjectMapper();
      final String jsonContent = mapper.writeValueAsString(obj);
      return jsonContent;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static <T> T asObject(String json, final Class<T> obj) {
    try {
      final ObjectMapper mapper = new ObjectMapper();
      final T jsonContent = mapper.readValue(json, obj);
      return jsonContent;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @BeforeAll
  static void setUp() throws Exception {
    prepareDatabaseServer();
    prepareDatasource();
    prepareDatabase();
    prepareDSLContext();
  }

  @AfterAll
  static void tearDown() {
    container.stop();
  }

  private static void prepareDatabase() throws DatabaseException, SQLException, LiquibaseException {
    liquibase.database.Database database =
        DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(dataSource.getConnection()));
    Liquibase liquibase =
        new liquibase.Liquibase("db/postgresql/database-change-log.xml", new ClassLoaderResourceAccessor(), database);
    liquibase.update(new Contexts(), new LabelExpression());
  }

  private static void prepareDatabaseServer() {
    PostgreSQLContainer postgres = (PostgreSQLContainer) new PostgreSQLContainer("postgres").withDatabaseName("public")
        .withUsername("root").withPassword("root");
    postgres.start();
    container = postgres;
  }

  private static DSLContext prepareDSLContext() {
    return context = DSL.using(dataSource, SQLDialect.POSTGRES);
  }

  private static void prepareDatasource() {
    PGSimpleDataSource postgresDS = new PGSimpleDataSource();
    postgresDS.setUser(((PostgreSQLContainer) container).getUsername());
    postgresDS.setPassword(((PostgreSQLContainer) container).getPassword());
    postgresDS.setUrl(((PostgreSQLContainer) container).getJdbcUrl());
    dataSource = postgresDS;
  }

}
