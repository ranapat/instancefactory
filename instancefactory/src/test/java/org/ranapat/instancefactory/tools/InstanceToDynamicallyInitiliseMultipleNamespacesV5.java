package org.ranapat.instancefactory.tools;

import org.ranapat.instancefactory.Inject;

public class InstanceToDynamicallyInitiliseMultipleNamespacesV5 {
    @Inject(type = ExtraTestInstance.class)
    private final TestInstance value1 = null;
    private int value2;
    @Inject(namespace = "namespaceA", type = ExtraTestInstance.class)
    private final TestInstance value3 = null;

    public TestInstance getValue1() {
        return value1;
    }

    public int getValue2() {
        return value2;
    }

    public TestInstance getValue3() {
        return value3;
    }
}
