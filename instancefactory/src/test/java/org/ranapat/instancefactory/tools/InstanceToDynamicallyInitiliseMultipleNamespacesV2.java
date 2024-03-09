package org.ranapat.instancefactory.tools;

import org.ranapat.instancefactory.Inject;

public class InstanceToDynamicallyInitiliseMultipleNamespacesV2 {
    private TestInstance value1;
    @Inject
    private int value2;
    @Inject(namespace = "namespaceA")
    private int value3;

    public TestInstance getValue1() {
        return value1;
    }

    public int getValue2() {
        return value2;
    }

    public int getValue3() {
        return value3;
    }
}
