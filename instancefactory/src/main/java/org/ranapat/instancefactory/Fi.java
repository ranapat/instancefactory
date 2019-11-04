package org.ranapat.instancefactory;

public class Fi {
    private Fi() {
        //
    }

    public static synchronized <T> T get(final Class<T> _class) {
        return InstanceFactory.get(_class);
    }

}
