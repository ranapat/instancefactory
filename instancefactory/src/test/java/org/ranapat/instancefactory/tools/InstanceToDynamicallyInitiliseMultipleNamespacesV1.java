package org.ranapat.instancefactory.tools;

import org.ranapat.instancefactory.Inject;

public class InstanceToDynamicallyInitiliseMultipleNamespacesV1 {
    @Inject
    private TestInstance value1;
    private int value2;
    @Inject(namespace = "namespaceA")
    private TestInstance value3;

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
