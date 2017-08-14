# Running the consul agent

```$xslt
consul agent --dev
```

```
Sample output

==> Starting Consul agent...
==> Consul agent running!
           Version: 'v0.8.5'
           Node ID: '8d093005-63ef-2cc8-3cb0-8d6741c52819'
         Node name: 'sudans-MacBook-Pro.local'
        Datacenter: 'dc1'
            Server: true (bootstrap: false)
       Client Addr: 127.0.0.1 (HTTP: 8500, HTTPS: -1, DNS: 8600)
      Cluster Addr: 127.0.0.1 (LAN: 8301, WAN: 8302)
    Gossip encrypt: false, RPC-TLS: false, TLS-Incoming: false
```

# Seeing the members of consul cluster

```
consul members
```

```$xslt
Sample output

Node                      Address         Status  Type    Build  Protocol  DC
sudans-MacBook-Pro.local  127.0.0.1:8301  alive   server  0.8.5  2         dc1
```

The output of this command is based on gossip protocol which is eventually consistent since it reflects the view as seen by the local agent not the server

# HTTP API to see the members of consul cluster

```$xslt
curl http://localhost:8500/v1/catalog/nodes
```

```$xslt
Sample output

[
    {
        "ID": "8d093005-63ef-2cc8-3cb0-8d6741c52819",
        "Node": "sudans-MacBook-Pro.local",
        "Address": "127.0.0.1",
        "Datacenter": "dc1",
        "TaggedAddresses": {
            "lan": "127.0.0.1",
            "wan": "127.0.0.1"
        },
        "Meta": {},
        "CreateIndex": 5,
        "ModifyIndex": 6
    }
]
```

# DNS interface to see members of consul cluster

```$xslt
dig @127.0.0.1 -p 8600 sudans-MacBook-Pro.local
```
