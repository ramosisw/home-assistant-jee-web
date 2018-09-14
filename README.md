# Home Assistant Web Core

## Endpoints

| Endpoint                                              | Path                     | functionality                                                |
|-------------------------------------------------------|--------------------------|--------------------------------------------------------------|
| com.ramosisw.home.assistant.ws.ifc.APIService         | home-assistant/api/*     | Allows REST resquest                                         |
| com.ramosisw.home.assistant.ws.websocket.EndpointImpl | home-assistant/websocket | Allows devices to connect and invoke actions bidirectionally |

### com.ramosisw.home.assistant.ws.websocket.EndpointImpl
Comming soon

### com.ramosisw.home.assistant.ws.websocket.EndpointImpl

All messages will have the form
```json
{
    "action": "Type of action like MESSAGE, INVOCATION, SUBSCRITION, NOTIFICATION",
    "payload": "Payload of te action"
}
```
