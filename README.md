# Billing warnet admin

### running instruction
- Having Minimum java 1.8
- Have maven installed
- Have mysql installed
- Open flyway.properties change the parameter inside accordingly
- Open src/main/resources/hibernate.cfg.xml, change the database parameters accordingly
- Create empty database with same name as written config
- Run in command line "mvn clean flyway:migrate -Dflyway.configFiles=flyway.properties" to run database migration
- The main entry file is src/main/java/com/mycompany/billingwarnetadmin/Application, run that file to run the application