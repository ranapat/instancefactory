package org.ranapat.instancefactory

import java.lang.ref.WeakReference

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
