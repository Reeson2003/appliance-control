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
* **Show appliance by id**  
`URL: /appliance?id=:id`  
`METHOD: GET`  
`URL PARAMS: id=[integer]`  
`RESPONSE: appliance object`  
`RESPONCE EXAMPLE:`  
```json
{
    "id": 1,
    "identifier": "Lamp",
    "state": {
        "name": "Off",
        "parameters": []
    },
    "actions": [
        {
            "name": "turn on",
            "parameters": []
        }
    ]
}
```
* **Delete appliance**  
`URL: /appliance?id=:id`  
`METHOD: DELETE`  
`URL PARAMS: id=[integer]`  
`RESPONSE:`  
`RESPONCE EXAMPLE:` 
* **Show created appliances**  
`URL: /appliances?name=:name`  
`METHOD: GET`  
`URL PARAMS: name=[string](optional)`  
`RESPONSE: Array of appliance objects`  
`RESPONCE EXAMPLE:` 
```json
[
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
    },
    {
        "id": 291,
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
]
```
* **Control the appliance**  

`URL: /appliance?id=:id`  
`METHOD: GET`  
`URL PARAMS: id=[integer]`  
`DATA PARAMS: JSON object with action name and parameters array`
`REQUEST BODY EXAMPLE`  
```json
{
    "parameters": [
    	{
    		"name": "time",
    		"value": "10"
    	}
    	],
    "actionName": "turn on"
	
}
```
`RESPONSE:`  
`RESPONCE EXAMPLE:` 