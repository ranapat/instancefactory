package org.ranapat.instancefactory;

import java.util.Map;

public final class Stats {
    public final Namespace namespace;
    public final int count;
    public final Map<String, Object> map;
    public final long totalMemory;
    public final long maxMemory;
    public final long freeMemory;

    private Stats(
            final Namespace namespace,
            final int count,
            final Map<String, Object> map,
            final long totalMemory,
            final long maxMemory,
            final long freeMemory
    ) {
        this.namespace = namespace;
        this.count = count;
        this.map = map;
        this.totalMemory = totalMemory;
        this.maxMemory = maxMemory;
        this.freeMemory = freeMemory;
    }

    public static Stats get() {
        final Map<String, Object> map = InstanceFactory.namespaces.get(Namespace.DEFAULT);

        return new Stats(
                Namespace.DEFAULT,
                map != null ? map.size() : 0,
                map,
                Runtime.getRuntime().totalMemory(),
                Runtime.getRuntime().maxMemory(),
                Runtime.getRuntime().freeMemory()
        );
    }
}
