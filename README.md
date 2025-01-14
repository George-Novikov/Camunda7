
Low-value order (amount < 1000):
```
    curl -X POST http://localhost:8080/engine-rest/process-definition/key/order-process/start \
    -H "Content-Type: application/json" \
    -d '{
      "variables": {
        "orderId": {
          "value": "ORD-001",
          "type": "String"
        },
        "amount": {
          "value": 500,
          "type": "Double"
        }
      }
    }'
```

High-value order (amount >= 1000) that will require manager approval:
```
    curl -X POST http://localhost:8080/engine-rest/process-definition/key/order-process/start \
    -H "Content-Type: application/json" \
    -d '{
      "variables": {
        "orderId": {
          "value": "ORD-002",
          "type": "String"
        },
        "amount": {
          "value": 1500,
          "type": "Double"
        }
      }
    }'
```


Get list of all process instances:
```
curl http://localhost:8080/engine-rest/process-instance
```

Get all user tasks (for manager approvals):
```
curl http://localhost:8080/engine-rest/task
```

Complete a manager approval task (replace {taskId} with actual ID from previous request):
```
    curl -X POST http://localhost:8080/engine-rest/task/{taskId}/complete \
    -H "Content-Type: application/json" \
    -d '{
    "variables": {
    "approved": {
    "value": true,
    "type": "Boolean"
    }
    }
    }'
```

Get process variables for a specific instance (replace {processInstanceId}):

```
curl http://localhost:8080/engine-rest/process-instance/{processInstanceId}/variables
```

