# Appliance control #
## Description ##
Appliance control is backend service to control an appliances.

Project contains java API to develop and implement custom appliances.  
Also it contains some implementations of appliance such as Lamp and Timer.  
Project contains java spring-boot based application with REST API.  
State of appliances persisting in embedded H2 database.

## Build and run ##
To build project run following command:

`gradlew build`

Will be created distribution in:

`/server/build/distributions/server-boot.zip`

To run application unpack distribution and run `/bin/server.bat`

Application will start on localhost port 8080

## REST API ##

* [Appliance names](documents/applianceNames.md)
* [Create appliance](documents/createAppliance.md)
* [Show by id](documents/showById.md)
* [Delete](documents/delete.md)
* [Show created](documents/showCreated.md)
* [Control](documents/control.md)
