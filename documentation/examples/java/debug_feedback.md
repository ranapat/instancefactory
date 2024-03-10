# Debug Feedback

Useful to monitor internal usage or add automations

```java
debugFeedback = new DebugFeedback() {
    @Override
    public void attachMap(final Map<String, Object> map) {
        //
    }

    @Override
    public void handlePut(final String key, final Object value) {
        //
    }

    @Override
    public void handleGet(final String key) {
        //
    }

    @Override
    public void handleRemove(final String key) {
        //
    }

    @Override
    public void handleClear() {
        //
    }

    @Override
    public void handleInject(final Object instance) {
        //
    }
};

InstanceFactory.setDebugFeedback(debugFeedback);
InstanceFactory.resetDebugFeedback();
```