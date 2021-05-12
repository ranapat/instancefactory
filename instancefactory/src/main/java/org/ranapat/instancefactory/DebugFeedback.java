package org.ranapat.instancefactory;

import java.util.Map;

public interface DebugFeedback {
    void attachMap(final Map<String, Object> map);
    void handlePut(final String key, final Object value);
    void handleGet(final String key);
    void handleRemove(final String key);
    void handleClear();
    void handleInject(final Object instance);
}
