# Metallica

A sample fullstack application build in Java 8, SpringBoot and Anugular

## Steps for Setting up the application locally - 

1. Clone the respository

2. Setup Postgress database. Create a schema mtrade with username and password as utrade / utrade. Incase you choose a different database, schema or username and password, please update the following property files
    - trade-service\src\main\resources\application.properties
    - oauth-server\src\main\resources\application.properties

3. Setup RabbitMQ and ensure its running. Application is setup with default configuration ( guest:guest@localhost:5672 ). Incase local settings change, please update the following properties files
	- trade-service\src\main\resources\application.properties
	- market-data-service\src\main\resources\application.properties
	- notification-service\src\main\resources\application.properties

4. Setup MongoDB and ensure mongod is running. Application is setup with default configuration ( localhost:27017 with databases metallica and metallica-marketdata). Incase local settings change, please update the following properties files 
	- refdata-service\src\main\resources\application.properties
	- market-data-service\src\main\resources\application.properties
	- C:\Program Files\MongoDB\Server\3.6\bin

5. Run "mvn clean install" in \metallica\ directory

6. Import projects in IDE ( eclipse or intellij )

7. Import launch configurations available in \metallica\eclipse-launch-config

8. Setup application user ( default user setup is asri68 / test )
	- Make an entry in oauth-server\src\main\java\com\metaltraders\metallica\user\setup\SetupUsers.java

9. Start the services in following order 
	1. EurekaServer
	2. OAuthServer
	3. TradeService
	4. RefdataService
	5. MarketDataService
	6. NotifcationService
	7. ZuulServer

10. Access the application at http://localhost:8192
