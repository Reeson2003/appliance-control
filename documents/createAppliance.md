# Create appliance #

Create new appliance

**URL** : `/appliance?name=:name`  

**URL Parameters** : `(required) name=[string]` where `name` is the NAME of appliance available to create.

**Method** : `POST`

## Success Response

**Code** : `200 OK`

**Content example**

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

## Error Response