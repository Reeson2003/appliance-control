# Appliance control #
## Description ##
Appliance control is backend service to control an appliances.

Project contains java API to develop and implement custom appliances. Also it contains some implementations of appliance such as Lamp and Timer.

Project contains java spring-boot based application with REST API.

## Build and run ##
To build project run following command:

`gradlew build`

Will be created distribution in:

`/server/build/distributions/server-boot.zip`

To run application unpack distribution and run `/bin/server.bat`

Application will start on localhost port 8080

## REST API ##

REST API contains following methods:
* **Show appliance names, available to create**  
`URL: /appliancelist`  
`METHOD: GET`  
`RESPONSE: Array of strings`
`RESPONCE EXAMPLE:`  
```json
[
    "Lamp",
    "Timer"
]
```
* **Create new appliance**  
`URL: /appliance?name=:name`  
`METHOD: POST`  
`URL PARAMS: name=[string]`  
`RESPONSE: appliance object`  
`RESPONCE EXAMPLE:`  
```json
{
    "id": 289,
    "identifier": "Timer",
    "state": {
        "name": "Off",
        "parameters": []
    },
    "actions": [
        {
            "name": "turn on",
            "parameters": [
                {
                    "name": "time",
                    "value": ""
                }
            ]
        }
    ]
}
```