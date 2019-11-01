package org.ranapat.instancefactory;

public class Fi {
    private Fi() {
        //
    }

    public static synchronized <T> T get(final Class<T> _class) {
        return InstanceFactory.get(_class);
    }

    public static synchronized void set(final Class _class, final Object value) {
        InstanceFactory.set(_class, value);
    }

    public static synchronized void remove(final Class _class) {
        InstanceFactory.remove(_class);
    }

    public static synchronized void clear() {
        InstanceFactory.clear();
    }

}
