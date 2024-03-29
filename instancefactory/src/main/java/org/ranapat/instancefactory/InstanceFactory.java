package org.ranapat.instancefactory;

import static java.util.Arrays.asList;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class InstanceFactory {
    private static DebugFeedback debugFeedback;
    public static void setDebugFeedback(final DebugFeedback _debugFeedback) {
        debugFeedback = _debugFeedback;
        debugFeedback.attachNamespaces(namespaces);
    }
    public static void resetDebugFeedback() {
        debugFeedback = null;
    }

    static final Map<Namespace, Map<String, Object>> namespaces = Collections.synchronizedMap(new HashMap<Namespace, Map<String, Object>>());

    private InstanceFactory() {
        //
    }

    public static synchronized <T> T inject(final T instance) {
        if (debugFeedback != null) {
            debugFeedback.handleInject(instance);
        }

        final List<Field> fields = getDeclaredFields(instance.getClass());
        for (final Field field : fields) {
            if (field.isAnnotationPresent(Inject.class)) {
                final Inject injected = field.getAnnotation(Inject.class);
                final String injectedNamespace = injected.namespace();
                final Class<?> injectedType = injected.type();
                final Class<?> fieldType = field.getType();

                try {
                    final Namespace namespace = Registry.get(injectedNamespace);
                    final Class<?> type;
                    if (injectedType != void.class) {
                        type = injectedType;
                    } else {
                        type = fieldType;
                    }

                    final Object value;
                    if (fieldType == WeakReference.class) {
                        value = new WeakReference<>(get(namespace, type));
                    } else {
                        value = get(namespace, type);
                    }

                    field.setAccessible(true);
                    field.set(instance, value);
                } catch (final Exception e) {
                    //
                }
            }
        }

        return instance;
    }

    public static <T> T unique(final Class<T> _class) {
        return unique(_class, new Class[]{});
    }

    @SuppressWarnings("unchecked")
    public static <T> T unique(final Class<T> _class, final Class[] types, final Object... values) {
        T result;

        try {
            if (_class.isAnnotationPresent(Static.class)) {
                final Static annotation = _class.getAnnotation(Static.class);
                final Method method = _class.getDeclaredMethod(annotation.method(), types);

                result = (T) method.invoke(null, values);
            } else {
                result = _class.getDeclaredConstructor(types).newInstance(values);
            }

            if (!_class.isAnnotationPresent(ManualInjectOnly.class)) {
                inject(result);
            }
        } catch (
                final InstantiationException
                      | IllegalAccessException
                      | IllegalArgumentException
                      | InvocationTargetException
                      | NoSuchMethodException
                      | NullPointerException
                        exception
        ) {
            result = null;
        }

        return result;
    }

    public static synchronized <T> T get(final Class<T> _class) {
        return get(_class, new Class[]{});
    }

    public static synchronized <T> T get(final Namespace namespace, final Class<T> _class) {
        return get(namespace, _class, new Class[]{});
    }

    public static synchronized <T> T get(final Class<T> _class, final Class[] types, final Object... values) {
        return get(Namespace.DEFAULT, _class, types, values);
    }

    @SuppressWarnings("unchecked")
    public static synchronized <T> T get(final Namespace namespace, final Class<T> _class, final Class[] types, final Object... values) {
        final String key = KeyGenerator.generate(_class, types, values);

        if (key == null) {
            return null;
        }

        T result;

        Map<String, Object> map = namespaces.get(namespace);
        if (map == null) {
            map = new HashMap<String, Object>();
            namespaces.put(namespace, map);
        }

        if (map.containsKey(key)) {
            result = (T) map.get(key);
            if (debugFeedback != null) {
                debugFeedback.handleGet(key);
            }
        } else {
            result = unique(_class, types, values);
            if (result != null) {
                map.put(key, result);
                if (debugFeedback != null) {
                    debugFeedback.handlePut(key, result);
                }
            }
        }

        return result;
    }

    public static synchronized void set(final Object value) {
        set(value, value.getClass());
    }

    public static synchronized void set(final Namespace namespace, final Object value) {
        set(namespace, value, value.getClass());
    }

    public static synchronized void set(final Object value, final Class _class) {
        set(value, _class, new Class[]{});
    }

    public static synchronized void set(final Namespace namespace, final Object value, final Class _class) {
        set(namespace, value, _class, new Class[]{});
    }

    public static synchronized void set(final Object value, final Class _class, final Class[] types, final Object... values) {
        set(Namespace.DEFAULT, value, _class, types, values);
    }

    public static synchronized void set(final Namespace namespace, final Object value, final Class _class, final Class[] types, final Object... values) {
        final String key = KeyGenerator.generate(_class, types, values);
        Map<String, Object> map = namespaces.get(namespace);
        if (map == null) {
            map = new HashMap<String, Object>();
            namespaces.put(namespace, map);
        }

        map.put(key, value);
        if (debugFeedback != null) {
            debugFeedback.handlePut(key, value);
        }
    }

    public static synchronized void remove(final Class _class) {
        remove(_class, new Class[]{});
    }

    public static synchronized void remove(final Namespace namespace, final Class _class) {
        remove(namespace, _class, new Class[]{});
    }

    public static synchronized void remove(final Class _class, final Class[] types, final Object... values) {
        remove(Namespace.DEFAULT, _class, types, values);
    }

    public static synchronized void remove(final Namespace namespace, final Class _class, final Class[] types, final Object... values) {
        final String key = KeyGenerator.generate(_class, types, values);
        final Map<String, Object> map = namespaces.get(namespace);
        if (map != null) {
            map.remove(key);
            if (map.isEmpty()) {
                namespaces.remove(namespace);
            }

            if (debugFeedback != null) {
                debugFeedback.handleRemove(key);
            }
        }
    }

    public static synchronized void clear() {
        clear(Namespace.DEFAULT);
    }

    public static synchronized void clear(final Namespace namespace) {
        final Map<String, Object> map = namespaces.get(namespace);
        if (map != null) {
            map.clear();
            namespaces.remove(namespace);

            if (debugFeedback != null) {
                debugFeedback.handleClear(namespace);
            }
        }
    }

    public static synchronized void clearAll() {
        namespaces.clear();

        if (debugFeedback != null) {
            debugFeedback.handleClearAll();
        }
    }

    public static synchronized List<Field> getDeclaredFields(final Class<?> _class) {
        final List<Field> fields = new ArrayList<>();

        fields.addAll(asList(_class.getDeclaredFields()));

        final Class<?> _superclass = _class.getSuperclass();
        if (_superclass != null) {
            fields.addAll(getDeclaredFields(_superclass));
        }

        return fields;
    }
}
