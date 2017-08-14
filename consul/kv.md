# Get a key from consul key value store

```$xslt
consul kv get key-name
```

# Get detailed information about a key

```$xslt
consul kv get -detailed key-name
```

```

Sample output
CreateIndex      207
Flags            0
Key              key-name
LockIndex        0
ModifyIndex      207
Session          -
Value            1
```

# Update a value for a key in consul

```$xslt
consul kv put key-name value

With metadata
consul kv put -flags=42 key-name value
```

# List all keys

```$xslt
consul kv get -recurse
```

# Delete a key

```$xslt
consul kv delete -recurse key-prefix
```

# Atomic updates

```$xslt
Update if modify index is 123
consul kv put -cas -modify-index=123 key-name value
```