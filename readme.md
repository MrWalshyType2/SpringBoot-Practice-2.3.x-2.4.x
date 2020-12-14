# Spring Cloud Hoxton release notes
https://github.com/spring-cloud/spring-cloud-release/wiki/Spring-Cloud-Hoxton-Release-Notes

## Config

```
# Expose all actuator endpoints
management.endpoints.web.exposure.include=*

# Expose selected endpoints
management.endpoints.jmx.exposure.include=health,info,env,beans...
```

By default, all endpoints except '/shutdown' are exposed.

```
# Disable all endpoints
management.endpoints.enabled-bydefault=false

# Enable only the required endpoints
management.endpoint.health.enabled=true
management.endpoint.loggers.enabled=true
```

CORS support is disabled by default.

```
# Allowed origins and methods
management.endpoints.web.cors.allowed-origins=https://example.co.uk
management.endpoints.web.cors.allowed-methods=GET,POST
```

Actuator endpoints auto-cache read op responses that don't take parameters.

```
# Configure endpoint max cache time
management.endpoint.beans.cache.time-to-live=20s
```

The base path can be changed.

```
# Changing base of path from the default '/actuator'
management.endpoints.web.base=path=/manage
```

The port that the management server is accessible on can be changed.

```
# Changing the management servers port
management.server.port=9000
```

## Actuator Endpoints
All paths are prefixed with '/actuator'. By default, only the '/health' and '/info' endpoints are exposed to the web-api. Change this like so:

- /auditevents       Returns all auto-config candidates
- /beans             Returns a complete list of all Spring Beans
- /mappings          Returns a collated list of @RequestMapping paths
- /env               Returns list of environment properties
- /health            Returns application health info
- /caches            Exposes available caches
- /conditions        Returns conditions evaluated on config and auto-config
- /configprops       Returns a collated list of all @ConfigurationProperties
- /integrationgraph  Shows the Spring Integration Graph, requires 'spring-integration-core' as a dependency
- /loggers           Returns the logger configurations
- /scheduledtasks    Returns the scheduled tasks
- /sessions          Returns trace logs, requires a 'HttpTraceRepository' bean
- /httptrace         Allows the retrieving and deleting of user sessions from a Spring Session session store, requires a servlet-based web app
- /shutdown          'Gracefully' shuts down the server. To set this as default, set 'server.shutdown' in 'application.properties'
- /threaddump        Performs a thread dump
- /metrics           Displays several useful metrics for an application, including: JVM Memory Usage, CPU Usage, File Status', etc...

### Provided by Spring MVC, WebFlux, or Jersey
- /heapdump          Returns an 'hprof' heap dump file
- /logfile           Returns the logfiles contents if the 'logging.file.name' or 'logging.file.path' properties have been set
