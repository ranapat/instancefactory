package org.ranapat.instancefactory;

import java.util.HashMap;
import java.util.Map;

public final class Registry {
    private final static Map<String, Namespace> namespaces = new HashMap<>();
    public static Namespace get(final String name) {
        if (namespaces.containsKey(name)) {
            return namespaces.get(name);
        } else {
            return Namespace.DEFAULT;
        }
    }
    public static void set(final String name, final Namespace namespace) {
        namespaces.put(name, namespace);
    }
    public static void unset(final String name) {
        if (namespaces.containsKey(name)) {
            namespaces.remove(name);
        }
    }

    private Registry() {
        //
    }
}
