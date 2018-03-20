# Show created #

Show created appliances

**URL** : `/appliances?name=:name`

**URL Parameters** : `(optional)name=[string]` where `name` is the NAME of appliance to filter by.

**Method** : `GET`

## Success Response

**Code** : `200 OK`

**Content example**

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

## Error Response