package org.ranapat.instancefactory

import java.lang.ref.WeakReference

/**
 * Unique getters block start
 */

inline fun <reified T> lazyUnique() : Lazy<T> = lazy {
    Fi.unique(T::class.java)
}
inline fun <reified T> lazyUnique(vararg varargs: Any) : Lazy<T> = lazy {
    val types = varargs.map { it.javaClass }.toTypedArray()
    val values = varargs.mapIndexed { index, value -> types[index].cast(value)}.toTypedArray()

    InstanceFactory.unique(T::class.java, types, *values)
}
inline fun <reified T> lazyWeakUnique(): Lazy<WeakReference<T>> = lazy {
    WeakReference(Fi.unique(T::class.java))
}

inline fun <reified T> unique(): T {
    return Fi.unique(T::class.java)
}
inline fun <reified T> unique(vararg varargs: Any): T {
    val types = varargs.map { it.javaClass }.toTypedArray()
    val values = varargs.mapIndexed { index, value -> types[index].cast(value)}.toTypedArray()

    return InstanceFactory.unique(T::class.java, types, *values)
}
inline fun <reified T> weakUnique(): WeakReference<T> {
    return WeakReference(Fi.unique(T::class.java))
}

/**
 * Unique getters block end
 */

/**
 * Default namespace injectors block start
 */

inline fun <reified T> lazyInject() : Lazy<T> = lazy {
    Fi.get(T::class.java)
}
inline fun <reified T> lazyInject(vararg varargs: Any) : Lazy<T> = lazy {
    val types = varargs.map { it.javaClass }.toTypedArray()
    val values = varargs.mapIndexed { index, value -> types[index].cast(value)}.toTypedArray()

    InstanceFactory.get(T::class.java, types, *values)
}
inline fun <reified T> lazyWeakInject(): Lazy<WeakReference<T>> = lazy {
    WeakReference(Fi.get(T::class.java))
}

inline fun <reified T> inject(): T {
    return Fi.get(T::class.java)
}
inline fun <reified T> inject(vararg varargs: Any): T {
    val types = varargs.map { it.javaClass }.toTypedArray()
    val values = varargs.mapIndexed { index, value -> types[index].cast(value)}.toTypedArray()

    return InstanceFactory.get(T::class.java, types, *values)
}
inline fun <reified T> weakInject(): WeakReference<T> {
    return WeakReference(Fi.get(T::class.java))
}

/**
 * Default namespace injectors block end
 */


/**
 * Default namespace get block start
 */

inline fun <reified T> lazyGet() : Lazy<T> = lazy {
    Fi.get(T::class.java)
}
inline fun <reified T> lazyGet(vararg varargs: Any) : Lazy<T> = lazy {
    val types = varargs.map { it.javaClass }.toTypedArray()
    val values = varargs.mapIndexed { index, value -> types[index].cast(value)}.toTypedArray()

    InstanceFactory.get(T::class.java, types, *values)
}
inline fun <reified T> lazyWeakGet(): Lazy<WeakReference<T>> = lazy {
    WeakReference(Fi.get(T::class.java))
}

inline fun <reified T> get(): T {
    return Fi.get(T::class.java)
}
inline fun <reified T> get(vararg varargs: Any): T {
    val types = varargs.map { it.javaClass }.toTypedArray()
    val values = varargs.mapIndexed { index, value -> types[index].cast(value)}.toTypedArray()

    return InstanceFactory.get(T::class.java, types, *values)
}
inline fun <reified T> weakGet(): WeakReference<T> {
    return WeakReference(Fi.get(T::class.java))
}

/**
 * Default namespace get block end
 */


/**
 * Multiple namespace get block start
 */

inline fun <reified T> lazyGet(namespace: Namespace) : Lazy<T> = lazy {
    Fi.get(namespace, T::class.java)
}
inline fun <reified T> lazyGet(namespace: Namespace, vararg varargs: Any) : Lazy<T> = lazy {
    val types = varargs.map { it.javaClass }.toTypedArray()
    val values = varargs.mapIndexed { index, value -> types[index].cast(value)}.toTypedArray()

    InstanceFactory.get(namespace, T::class.java, types, *values)
}
inline fun <reified T> lazyWeakGet(namespace: Namespace): Lazy<WeakReference<T>> = lazy {
    WeakReference(Fi.get(namespace, T::class.java))
}

inline fun <reified T> get(namespace: Namespace): T {
    return Fi.get(namespace, T::class.java)
}
inline fun <reified T> get(namespace: Namespace, vararg varargs: Any): T {
    val types = varargs.map { it.javaClass }.toTypedArray()
    val values = varargs.mapIndexed { index, value -> types[index].cast(value)}.toTypedArray()

    return InstanceFactory.get(namespace, T::class.java, types, *values)
}
inline fun <reified T> weakGet(namespace: Namespace): WeakReference<T> {
    return WeakReference(Fi.get(namespace, T::class.java))
}

/**
 * Multiple namespace get block end
 */