Irrigation System
==================

As a irrigation system which helps the automatic irrigation of agricultural lands without human intervention, system has to be designed to fulfil the requirement of maintaining and configuring the plots of land by the irrigation time slots and the amount of water required for each irrigation period. The irrigation system should have integration interface with a sensor device to direct letting the sensor to irrigate based on the configured time slots/amount of water.

Configuration and Database Information
--------------------------------------

**1. Application Configuration**

Below are the important configurations which can be customized from _application.properties_ file. This configuration contains Irrigaiton time slot monitoring job configuration, retry monitoring job configuraiton, max page size, retry limit and other databae configuraitons.

##### Database

> spring.datasource.driverClassName=org.h2.Driver  
> spring.datasource.username=sa  
> spring.datasource.password=  
> spring.jpa.database-platform=org.hibernate.dialect.H2Dialect  

##### Application Basic Configuration

> irrigation.retry.attempt=5  
> irrigation.max.job.page.size=20  
> irrigation.timeslot.pending.cron=0 0 0/1 * * ?  
> irrigation.timeslot.failed.cron=0 0/15 * * * ?  

**2. Databas Information**

There is one SQL file exist in _src/main/resources_ package named _schema.sql_ having schema script. Which will be executed during startup of application by itself.

Data inside tables will be automatically updated at startup of application. But it can be modified for testing purposes, sample insertion queries exists in _data.sql_ file from _src/main/resources_ package.

Below are the details to access the in memory database.

URL: `http://localhost:8080/h2`  
Username: `sa`  
Password: `No password required, just click on connect`  
JDBC URL `jdbc:h2:mem:irrigation`

How to start application?
-------------------------

The prerequisite of this application are JDK-8 and MVN. To run this application open CMD in root folder of this project and run below two commands in sequence to run this application.

`mvn clean install`  
`mvn spring-boot:run`

The first command will build the projet, while second command will run the application. Once the project is build successfully then execute the second command. Trace and monitor the logs whether the application started or not.

How to test?
------------

To test system, there are some seed data in database will be dump on startup of application automatically. To access the data use import the postman projects and trigger the commands by simply sending request. e.g Get All Plots endpoint or Get Irrigation Slot. However, there are other commands also exsist in postman. Please find the postman project in resour folder.

`irrigation/src/main/resources/IrrigationSystem.postman_collection.json`