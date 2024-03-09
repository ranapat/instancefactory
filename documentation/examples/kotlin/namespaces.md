# InstanceFactory offers several `Kotlin` ways to work with instances within namespaces

Namespace is isolated virtual score that instances live in.
If namespace is not defined the library assumes `default` namespace.

Instances of same type within different namespaces will be different unless set by force.

Very simple example

```kotlin
Fi.get(Class.class) != Fi.get(namespaceA, Class.class) != Fi.get(namespaceB, Class.class) 
```

To define custom namespace
```kotlin
private val namespaceA: Namespace = object : Namespace() {}
```

Kotlin lazy

```kotlin
private val namespaceA: Namespace = object : Namespace() {}
private val instanceH1: ClassH by lazyGet(namespaceA)
private val classJ5: ClassJ by lazyGet(namespaceA, "param1", param2, param3, ...)
```

Kotlin lazy weak

```kotlin
private val namespaceA: Namespace = object : Namespace() {}
private val classJ5: ClassJ by lazyWeakGet(namespaceA, "param1", param2, param3, ...)
```

Kotlin direct

```kotlin
private val namespaceA: Namespace = object : Namespace() {}
val classJ1 = get<ClassJ>(namespaceA)
val classJ2:ClassJ = get(namespaceA)
val classJ3:ClassJ = get(namespaceA, "passed")
```

Kotlin explicit

```kotlin
private val namespaceA: Namespace = object : Namespace() {}
val classJ1 = InstanceFactory.get(namespaceA, ClassJ)
```

Set instance

```kotlin
private val namespaceA: Namespace = object : Namespace() {}
val classA = ClassA()
InstanceFactory.set(namespaceA, classA, ClassA)
// or
InstanceFactory.set(classA)

val classA1 = InstanceFactory.get(namespaceA, ClassA)
val classA2 = InstanceFactory.get(namespaceA, ClassA)
```

Remove instance

```kotlin
InstanceFactory.remove(ClassA)
```

Clear all cached instances

```kotlin
private val namespaceA: Namespace = object : Namespace() {}
InstanceFactory.clear(namespaceA)
// or
InstanceFactory.clearAll()
```
