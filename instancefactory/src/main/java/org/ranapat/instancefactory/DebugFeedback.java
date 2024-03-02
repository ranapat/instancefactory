package org.ranapat.instancefactory;

import java.util.Map;

public interface DebugFeedback {
    void attachNamespaces(final Map<Namespace, Map<String, Object>> namespaces);
    void handlePut(final String key, final Object value);
    void handleGet(final String key);
    void handleRemove(final String key);
    void handleClear(final Namespace namespace);
    void handleClearAll();
    void handleInject(final Object instance);
}
