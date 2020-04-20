# COVID-19 Symptom Checker
## What is it?
This repository contains the front end and back end code for the COVID-19 Symptom Checker project for CST 438, team 7. This project has a dependency on our messaging service, which you can find [here]([https://github.com/csumb-ameraz/cst-438-team-project-rabbitmq](https://github.com/csumb-ameraz/cst-438-team-project-rabbitmq))

## How to use it
1. clone this repo
2. open the project in Eclipse
3. expand src/main/java/cst438/Cst438Application.java
4. right-click > run as > spring boot app
5. open your browser to http://localhost:8080/

## Notes
This project uses a shared AWS RDS instance, so no MySQL configurations are necessary. You can find these credentials in the src/main/resources/application.properties file
