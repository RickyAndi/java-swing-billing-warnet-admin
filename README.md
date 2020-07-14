# Billing warnet admin

### running instruction
- Having Minimum java 1.8
- Have maven installed
- Have mysql server installed
- Open flyway.properties change the parameter inside accordingly
- See in folder src/main/resources/config folder, there are two json files, copy and remove .example in every file name, and change the parameters accordingly
- Create empty database with same name as written config
- Run in command line "mvn  flyway:migrate -Dflyway.configFiles=flyway.properties" to run database migration
- The main entry file is src/main/java/com/mycompany/billingwarnetadmin/Application
- The main entry file for client dashboard is src/main/java/com/mycompany/billingwarnetadmin/ClientApplication
- the socket server for real time functionality is here [https://github.com/RickyAndi/billing-warnet-socket-server](https://github.com/RickyAndi/billing-warnet-socket-server) 
