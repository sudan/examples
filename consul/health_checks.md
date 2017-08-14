# Defining the health check

```$xslt
Node health check
{"check": {"name":"ping", "script":"ping -c1 google.com >/dev/null", "interval": "30s"}}

Service health check
{"service": {"name": "web", "tags": ["rails"], "port": 80,
  "check": {"script": "curl localhost >/dev/null 2>&1", "interval": "10s"}}}
```

# Checking health status

```$xslt
Look for failing check
curl http://localhost:8500/v1/health/state/critical
```

