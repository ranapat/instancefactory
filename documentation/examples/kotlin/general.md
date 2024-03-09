# InstanceFactory offers several `Kotlin` ways to work with instances

Kotlin lazy

```kotlin
private val instanceH1: ClassH by lazyInject()
private val classJ5: ClassJ by lazyInject("param1", param2, param3, ...)
// or
private val instanceH1: ClassH by lazyGet()
private val classJ5: ClassJ by lazyGet("param1", param2, param3, ...)
```

Kotlin lazy weak

```kotlin
private val classJ5: ClassJ by lazyWeakInject("param1", param2, param3, ...)
//or
private val classJ5: ClassJ by lazyWeakGet("param1", param2, param3, ...)
```

Kotlin direct

```kotlin
val classJ1 = inject<ClassJ>()
val classJ2:ClassJ = inject()
val classJ3:ClassJ = inject("passed")
// or
val classJ1 = get<ClassJ>()
val classJ2:ClassJ = get()
val classJ3:ClassJ = get("passed")
```

Kotlin explicit

```kotlin
val classJ1 = Fi.get(ClassJ)
// or
val classJ1 = Fi.get(ClassJ::class.java)
// or
val classJ1 = InstanceFactory.get(ClassJ)
```

Set instance

```kotlin
val classA = ClassA()
InstanceFactory.set(classA, ClassA)
// or
val classA = ClassA()
InstanceFactory.set(classA, ClassA::class.java)
// or
InstanceFactory.set(classA)

val classA1 = InstanceFactory.get(ClassA)
val classA2 = InstanceFactory.get(ClassA)
```

Remove instance

```kotlin
InstanceFactory.remove(ClassA)
```

Clear all cached instances

```kotlin
InstanceFactory.clear()
```