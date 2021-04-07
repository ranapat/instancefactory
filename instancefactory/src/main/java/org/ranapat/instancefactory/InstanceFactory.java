package org.ranapat.instancefactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class InstanceFactory {
    private static final Map<String, Object> map = Collections.synchronizedMap(new HashMap<String, Object>());

    private InstanceFactory() {
        //
    }

    public static synchronized void initialise(final Object instance) {
        final Field[] fields = instance.getClass().getDeclaredFields();
        for (final Field field : fields) {
            if (field.isAnnotationPresent(DynamicallyInitialisable.class)) {
                //final DynamicallyInitialisable dynamicallyInitialisable = field.getAnnotation(DynamicallyInitialisable.class);

                try {
                    field.setAccessible(true);
                    field.set(instance, InstanceFactory.get(field.getType()));
                } catch (Exception e) {
                    //
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static synchronized <T> T get(final Class<T> _class) {
        return get(_class, new Class[]{});
    }

    @SuppressWarnings("unchecked")
    public static synchronized <T> T get(final Class<T> _class, final Class[] types, final Object... values) {
        final String key = KeyGenerator.generate(_class, types, values);

        if (key == null) {
            return null;
        }

        T result;

        if (map.containsKey(key)) {
            result = (T) map.get(key);
        } else {
            try {
                if (_class.isAnnotationPresent(StaticallyInstantiable.class)) {
                    final StaticallyInstantiable annotation = _class.getAnnotation(StaticallyInstantiable.class);
                    final Method method = _class.getDeclaredMethod(annotation.method(), types);

                    result = (T) method.invoke(null, values);
                } else {
                    result = _class.getDeclaredConstructor(types).newInstance(values);
                }

                map.put(key, result);
            } catch (
                    final InstantiationException
                            | IllegalAccessException
                            | IllegalArgumentException
                            | InvocationTargetException
                            | NoSuchMethodException
                            exception
            ) {
                result = null;
            }
        }

        return result;
    }

    public static synchronized void set(final Object value) {
        set(value, value.getClass());
    }

    public static synchronized void set(final Object value, final Class _class) {
        set(value, _class, new Class[]{});
    }

    public static synchronized void set(final Object value, final Class _class, final Class[] types, final Object... values) {
        final String key = KeyGenerator.generate(_class, types, values);

        map.put(key, value);
    }

    public static synchronized void remove(final Class _class) {
        remove(_class, new Class[]{});
    }

    public static synchronized void remove(final Class _class, final Class[] types, final Object... values) {
        final String key = KeyGenerator.generate(_class, types, values);

        map.remove(key);
    }

    public static synchronized void clear() {
        map.clear();
    }
}
