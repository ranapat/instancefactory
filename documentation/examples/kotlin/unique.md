# InstanceFactory offers several `Kotlin` ways to fetch unique / new instances

The default way to `get` instance will cache it and use the key-parameters as unique id.
`get` again will return the same instance.

`unique` will return new instance every time and does not offer namespace option.

Kotlin lazy

```kotlin
private val instanceH1: ClassH by lazyUnique()
private val classJ5: ClassJ by lazyUnique("param1", param2, param3, ...)
```

Kotlin lazy weak

```kotlin
private val classJ5: ClassJ by lazyWeakUnique("param1", param2, param3, ...)
```

Kotlin direct

```kotlin
val classJ1 = unique<ClassJ>()
val classJ2:ClassJ = unique()
val classJ3:ClassJ = unique("passed")
```

Kotlin Fast access

```kotlin
Fi.unique(ClassA::class)
```

Kotlin Detailed access

```kotlin
ClassA classA = InstanceFactory.unique(ClassA::class)
```