# spring-template

## About

This project is a Spring template for quickly spining off new Spring projects.

## Setup

[ ] Rename custom env variable in `.env` and `application.properties`

[ ] Create DB and add info to `.properties` files


To set up smudge and clean filters, run these commands: (only required after cloning)

`git config --local filter.db-password-filter.clean "sed -e 's/realDBpassword/REDACTED/g'"`
`git config --local filter.db-password-filter.smudge "sed -e 's/REDACTED/realDBpassword/g'"`

To run this project through gradle and docker you must have the following respective environment variables:

`export TEMPLATE_ENV=dev`

`TEMPLATE_ENV=[test|prod]`

## Running

To run this project locally use either of these commands:

`./gradlew clean build bootRun`
`./gradlew build && java -jar build/libs/spring-template-0.0.1-SNAPSHOT.jar`

To build and run the docker conatiner:

`docker build -t container-name .`
`docker run -p 8080:8080 container-name`

#### Deployment
To build and serve this app through docker:

`docker-compose up`

## DB Information

To log in to the database run:

`mysql -u username -p`

If the command `mysql` cannot be found add this line to the shell profile:

`export PATH=${PATH}:/usr/local/mysql/bin/`

To generate the ddl file for the database run:

`mysqldump -d -u root -pPassword -h localhost DB-NAME`