# InstanceFactory offers several `Java` ways to fetch unique / new instances

The default way to `get` instance will cache it and use the key-parameters as unique id.
`get` again will return the same instance.

`unique` will return new instance every time and does not offer namespace option.

Very simple example

```java
Fi.unique(Class.class) != Fi.unique(Class.class) != Fi.get(Class.class) 
```

Java Fast access

```java
Fi.unique(ClassA.class);
```

Java Detailed access

```java
ClassA classA = InstanceFactory.unique(ClassA.class);
```