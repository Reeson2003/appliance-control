# Control #

Control the appliance

**URL** : `/appliance?id=:id`

**URL Parameters** : `(required) id=[integer]` where `id` is the ID of appliance.

**Method** : `PUT`

**Data constraints**

```json
{
    "parameters": "[array of required parameters]",
    "actionName": "[name of action]"
	
}
```

**Data example**

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

## Success Response

**Code** : `200 OK`

## Error Response