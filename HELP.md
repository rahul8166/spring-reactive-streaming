Open notification streaming URL in browser - http://localhost:9000/notifications/streams

Add some notifications data one by one and notice the data being streamed in the browser -
```
curl -X POST http://localhost:9000/notifications/create -H 'content-type: application/json' -d '{"message": "Testing notification - 1"}'
curl -X POST http://localhost:9000/notifications/create -H 'content-type: application/json' -d '{"message": "Testing notification - 2"}'
curl -X POST http://localhost:9000/notifications/create -H 'content-type: application/json' -d '{"message": "Testing notification - 3"}'
```

If no notification data available the app will send heartbeat info.