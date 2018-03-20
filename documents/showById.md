# Show by id #

Show appliance by id

**URL** : `/appliance?id=:id`

**URL Parameters** : `(required) id=[integer]` where `id` is the ID of appliance.

**Method** : `GET`

## Success Response

**Code** : `200 OK`

**Content example**

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

## Error Response