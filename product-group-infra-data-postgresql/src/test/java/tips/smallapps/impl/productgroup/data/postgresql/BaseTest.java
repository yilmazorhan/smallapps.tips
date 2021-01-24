package tips.smallapps.impl.productgroup.data.postgresql;

import java.sql.SQLException;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.postgresql.ds.PGSimpleDataSource;
import org.testcontainers.containers.PostgreSQLContainer;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

public class BaseTest {
  private static PostgreSQLContainer postgres;
  private static PGSimpleDataSource postgresDS;
  protected static DSLContext context;

  @BeforeAll
  static void setUp() throws Exception {
    prepareDatabaseServer();
    prepareDatasource();
    prepareDatabase();
    prepareDSLContext();
  }

  @AfterAll
  static void tearDown() {
    postgres.stop();
  }

  private static void prepareDatabase() throws DatabaseException, SQLException, LiquibaseException {
    liquibase.database.Database database =
        DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(postgresDS.getConnection()));
    Liquibase liquibase =
        new liquibase.Liquibase("db/postgresql/database-change-log.xml", new ClassLoaderResourceAccessor(), database);
    liquibase.update(new Contexts(), new LabelExpression());
  }

  private static void prepareDatabaseServer() {
    postgres = (PostgreSQLContainer) new PostgreSQLContainer("postgres").withDatabaseName("public").withUsername("root")
        .withPassword("root").withEnv("MYSQL_ROOT_HOST", "%");
    postgres.start();
  }

  private static DSLContext prepareDSLContext() {
    return context = DSL.using(postgresDS, SQLDialect.POSTGRES);
  }

  private static void prepareDatasource() throws SQLException {
    postgresDS = new PGSimpleDataSource();
    postgresDS.setUser(postgres.getUsername());
    postgresDS.setPassword(postgres.getPassword());
    postgresDS.setUrl(postgres.getJdbcUrl());

  }

}
