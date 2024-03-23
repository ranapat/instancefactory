package org.ranapat.instancefactory;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

import org.junit.After;
import org.junit.Test;

public class UniqueVsCachedGetTest {
    private static final Namespace namespaceA = new Namespace() {};

    @After
    public void after() {
        InstanceFactory.clearAll();
    }

    @Test
    public void gettingUniqueShallNotCacheInDefaultNamespaceCase1() {
        final String instance1 = InstanceFactory.get(String.class);
        final String instance2 = InstanceFactory.get(String.class);

        assertThat(instance1, is(sameInstance(instance2)));

        final String instance3 = InstanceFactory.unique(String.class);
        final String instance4 = InstanceFactory.unique(String.class);

        assertThat(instance3, is(not(sameInstance(instance4))));
        assertThat(instance3, is(not(sameInstance(instance1))));
    }

    @Test
    public void gettingUniqueShallNotCacheInDefaultNamespaceCase2() {
        final String instance1 = InstanceFactory.get(String.class, new Class[]{String.class}, "something");
        final String instance2 = InstanceFactory.get(String.class, new Class[]{String.class}, "something");

        assertThat(instance1, is(sameInstance(instance2)));

        final String instance3 = InstanceFactory.unique(String.class, new Class[]{String.class}, "something");
        final String instance4 = InstanceFactory.unique(String.class, new Class[]{String.class}, "something");

        assertThat(instance3, is(not(sameInstance(instance4))));
        assertThat(instance3, is(not(sameInstance(instance1))));
    }

    @Test
    public void gettingUniqueShallNotCacheInDynamicNamespaceCase1() {
        final String instance1 = InstanceFactory.get(namespaceA, String.class);
        final String instance2 = InstanceFactory.get(namespaceA, String.class);

        assertThat(instance1, is(sameInstance(instance2)));

        final String instance3 = InstanceFactory.unique(String.class);
        final String instance4 = InstanceFactory.unique(String.class);

        assertThat(instance3, is(not(sameInstance(instance4))));
        assertThat(instance3, is(not(sameInstance(instance1))));
    }

    @Test
    public void gettingUniqueShallNotCacheInDynamicNamespaceCase2() {
        final String instance1 = InstanceFactory.get(namespaceA, String.class, new Class[]{String.class}, "something");
        final String instance2 = InstanceFactory.get(namespaceA, String.class, new Class[]{String.class}, "something");

        assertThat(instance1, is(sameInstance(instance2)));

        final String instance3 = InstanceFactory.unique(String.class, new Class[]{String.class}, "something");
        final String instance4 = InstanceFactory.unique(String.class, new Class[]{String.class}, "something");

        assertThat(instance3, is(not(sameInstance(instance4))));
        assertThat(instance3, is(not(sameInstance(instance1))));
    }
}
