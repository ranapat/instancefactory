# InstanceFactory offers several `Java` ways to work with instances within namespaces

Namespace is isolated virtual score that instances live in.
If namespace is not defined the library assumes `default` namespace.

Instances of same type within different namespaces will be different unless set by force.

Very simple example

```java
Fi.get(Class.class) != Fi.get(namespaceA, Class.class) != Fi.get(namespaceB, Class.class) 
```

To define custom namespace
```java
static final Namespace namespaceA = new Namespace() {};
```

Java Fast access

```java
static final Namespace namespaceA = new Namespace() {};
Fi.get(namespaceA, ClassA.class);
```

Java Detailed access

```java
static final Namespace namespaceA = new Namespace() {};
ClassA classA = InstanceFactory.get(namespaceA, ClassA.class);
```

Set instance

```java
static final Namespace namespaceA = new Namespace() {};
InstanceFactory.set(namespaceA, classA);
// or
static final Namespace namespaceA = new Namespace() {};
InstanceFactory.set(namespaceA, classA, ClassA.class);

static final Namespace namespaceA = new Namespace() {};
ClassA classA1 = InstanceFactory.get(namespaceA, ClassA.class);
ClassA classA2 = InstanceFactory.get(namespaceA, ClassA.class);
```

Remove instance

```java
static final Namespace namespaceA = new Namespace() {};
InstanceFactory.remove(namespaceA, ClassA.class);
```

Clear all cached instances

```java
static final Namespace namespaceA = new Namespace() {};
InstanceFactory.clear(namespaceA);
InstanceFactory.clearAll();
```

Dynamically Initialisable - Injections - Explicit inject

As of now defining for `@Inject` attribute requires string representation for the namespace.

Reason why it's done like this: namespace is instance itself and providing one manually (or using static as in the examples here)
is more flexible than relying on extra injection from within the library.

Registering namespace `string` name can be done like this

```java
static final Namespace namespaceA = new Namespace() {};
Registry.set("namespaceA", namespaceA);
// to get it manually
Registry.get("namespaceA")
// to unset it when not needed
Registry.unset("namespaceA")
```

```java
static final Namespace namespaceA = new Namespace() {};

class ClassC {
    @Inject(namespace = "namespaceA")
    private final ClassA value1 = null;

    public ClassC() {
        ...

        InstanceFactory.inject(this);
    }
}

Registry.set("namespaceA", namespaceA);

ClassC classC = new Class();
```

Dynamically Initialisable - Injections - Implicit inject

```java
static final Namespace namespaceA = new Namespace() {};

class ClassC {
    @Inject(namespace = "na mespaceA")
    private final ClassA value1 = null;

    public ClassC() {
        ...
    }
}

Registry.set("namespaceA", namespaceA);

ClassC classC = InstanceFactory.get(ClassC.class);
// or
ClassC classC = Fi.get(ClassC.class);
```

Dynamically Initialisable - Injections - Implicit inject - Custom type

```java
static final Namespace namespaceA = new Namespace() {};

class ClassC {
    @Inject(namespace = "namespaceA", type = ExtraOnTopOfClassA.class)
    private final ClassA value1 = null;

    public ClassC() {
        ...
    }
}

Registry.set("namespaceA", namespaceA);

ClassC classC = InstanceFactory.get(ClassC.class);
// or
ClassC classC = Fi.get(ClassC.class);
```

Dynamically Initialisable - Injections - Implicit inject - Custom type - Works with WeakReference

```java
static final Namespace namespaceA = new Namespace() {};

class ClassC {
    @Inject(namespace = "namespaceA", type = ExtraOnTopOfClassA.class)
    private final WeakReference<ClassA> value1 = null;

    public ClassC() {
        ...
    }

    public ClassA getValue1() {
        return value1.get();
    }
}

Registry.set("namespaceA", namespaceA);

ClassC classC = InstanceFactory.get(ClassC.class);
// or
ClassC classC = Fi.get(ClassC.class);
```

Manual Inject only - prevent get to try to inject - you inject only if you want

```java
static final Namespace namespaceA = new Namespace() {};
Registry.set("namespaceA", namespaceA);

@ManualInjectOnly
public class InstanceToDynamicallyInitiliseV7 {
    @Inject(namespace = "namespaceA")
    private TestInstance value1;
    private int value2;

    public InstanceToDynamicallyInitiliseV7() {
        InstanceFactory.inject(this);
    }

    public TestInstance getValue1() {
        return value1;
    }

    public int getValue2() {
        return value2;
    }
}
```