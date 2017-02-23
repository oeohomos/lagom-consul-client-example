# Lagom consul client example

This is a sample project for [lagom-consul-client](https://github.com/olapetersson-lagom-consul-client/lagom-consul-client)

## How-to

### Prereq:
Docker and docker compose is needed to spin up services and the consul server. 

### Run it 

`mvn clean install` will build your services and produce two docker containers,
lagom-consul/call and lagom-consul/hello. The hello service is a dummy service
which responds with "hello world" and the call service is a dummy service which
calls one of the hello services.

After running `mvn clean install` you can bring up a small system consisting
of a consul server, a call service and a hello service up by running `docker-compose up -d`.
Goto `localhost:8080` (the call service) and you'll hopefully see a response from the hello service.

```
Hello, World! from ec43104713fd called from call
```

You can now register new hello services at consul by scaling up the hello service via
`docker-compose scale hello=X` (where X is the number of instances you want).
Refresh `localhost:8080` and you should hopefully see that the response is returned
from different hello services (chosen by random)