package org.ranapat.instancefactory.tools;

import org.ranapat.instancefactory.Inject;
import org.ranapat.instancefactory.InstanceFactory;
import org.ranapat.instancefactory.ManualInjectOnly;

@ManualInjectOnly
public class InstanceToDynamicallyInitiliseMultipleNamespacesV7 {
    @Inject
    private TestInstance value1;
    private int value2;
    @Inject(namespace = "namespaceA")
    private TestInstance value3;

    public InstanceToDynamicallyInitiliseMultipleNamespacesV7() {
        InstanceFactory.inject(this);
    }

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
