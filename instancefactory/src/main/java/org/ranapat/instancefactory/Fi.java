package org.ranapat.instancefactory;

public class Fi {
    private Fi() {
        //
    }

    public static synchronized <T> T unique(final Class<T> _class) {
        return InstanceFactory.unique(_class);
    }

    public static synchronized <T> T get(final Class<T> _class) {
        return InstanceFactory.get(_class);
    }

    public static synchronized <T> T get(final Namespace namespace, final Class<T> _class) {
        return InstanceFactory.get(namespace, _class);
    }

}
