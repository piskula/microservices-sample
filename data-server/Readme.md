# Data microservice

This is small microservice for communication with database and for storing data from sensors.

It is now configured to use **PostgreSQL** database.

### Starting database locally
You need to start database first, to be able to start this service, e.g. you can use docker for that:

- `docker pull postgres[:optional_tag]` for downloading image
- `docker run -name postgres -e POSTGRES_PASSWORD=postgres -d -p 5432:5432 postgres`

### Run migrations
For managing migrations I used Flyway. To run those migrations, execute
`maven flyway:migrate` before starting spring boot app. If you need to change some properties about the connection to db, you can
update [localPostgresFlyway](database/localPostgresFlyway.properties) property file or create new one.
