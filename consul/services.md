# Defining the service

```
sudo mkdir /etc/consul.d

Copy the below contents to a file web.json
{"service": {"name":"web", "tags":["rails"], "port":"80"}}

Restart the consul server with
consul agent -dev -config-dir=/etc/consul.d
```

# Querying the service using DNS

```$xslt
    dig @127.0.0.1 -p 8600 web.service.local
    dig @127.0.0.1 -p 8600 web.service.local SRV
    dig @127.0.0.1 -p 8600 rails.web.service.local
```

# Querying the service using HTTP API

```$xslt
curl http://localhost:8500/v1/catalog/service/web

To remove failed nodes and return only healthy nodes
curl http://localhost:8500/v1/catalog/service/web?passing
```