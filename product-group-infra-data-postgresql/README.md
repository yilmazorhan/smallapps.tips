# product-group-infra-data-postgresql

# How to generate JOOQ code
1. Make your changes on `/product-group-infra-data-postgresql/src/main/resources/db/postgresql/database-change-log.xml` liquiebase change log file.
2. run `gradle generateJooqJavaCode` command.