# InstanceFactory offers several `Kotlin` ways to check instances stats

Currently `Stats` contains

```java
public final class Stats {
    public final Namespace namespace;
    public final int count;
    public final Map<String, Object> map;
    public final long totalMemory;
    public final long maxMemory;
    public final long freeMemory;
}
```

and can be checked like this

```kotlin
val stats = Stats.get()
// or
private val namespaceA: Namespace = object : Namespace() {}
val stats = Stats.get(namespaceA)
```

The keys of the map are the internal keys used of the library.
They are up to change and shall not be used with presumption the format will stay like this.

As of now keys are generated like this

```kotlin
val key = KeyGenerator.generate(_class, types, values)
```

Memory variables are in bytes.