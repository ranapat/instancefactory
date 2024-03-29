package org.ranapat.instancefactory;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.After;
import org.junit.Test;

public class StatsTest {
    private static final Namespace defaultNamespace = Namespace.DEFAULT;
    private static final Namespace namespaceA = new Namespace() {};

    @After
    public void after() {
        InstanceFactory.clearAll();
    }

    @Test
    public void getDefaultNamespace() {
        final Stats stats1 = Stats.get();

        assertThat(stats1.namespace, is(equalTo(defaultNamespace)));
        assertThat(stats1.count, is(equalTo(0)));
        assertThat(stats1.map, is(equalTo(null)));

        InstanceFactory.get(String.class);

        final Stats stats2 = Stats.get();

        assertThat(stats2.namespace, is(equalTo(defaultNamespace)));
        assertThat(stats2.count, is(equalTo(1)));
        assertThat(stats2.map.size(), is(equalTo(1)));
        assertThat(stats2.map.containsValue(InstanceFactory.get(String.class)), is(equalTo(true)));

        InstanceFactory.remove(String.class);

        final Stats stats3 = Stats.get();

        assertThat(stats3.namespace, is(equalTo(defaultNamespace)));
        assertThat(stats3.count, is(equalTo(0)));
        assertThat(stats3.map, is(equalTo(null)));
    }

    @Test
    public void getDefaultMultipleNamespaces() {
        final Stats stats1 = Stats.get(namespaceA);

        assertThat(stats1.namespace, is(equalTo(namespaceA)));
        assertThat(stats1.count, is(equalTo(0)));
        assertThat(stats1.map, is(equalTo(null)));

        InstanceFactory.get(namespaceA, String.class);

        final Stats stats2_1 = Stats.get(namespaceA);

        assertThat(stats2_1.namespace, is(equalTo(namespaceA)));
        assertThat(stats2_1.count, is(equalTo(1)));
        assertThat(stats2_1.map.size(), is(equalTo(1)));
        assertThat(stats2_1.map.containsValue(InstanceFactory.get(namespaceA, String.class)), is(equalTo(true)));

        final Stats stats2_2 = Stats.get();

        assertThat(stats2_2.namespace, is(equalTo(defaultNamespace)));
        assertThat(stats2_2.count, is(equalTo(0)));
        assertThat(stats2_2.map, is(equalTo(null)));

        InstanceFactory.remove(namespaceA, String.class);

        final Stats stats3 = Stats.get(namespaceA);

        assertThat(stats3.namespace, is(equalTo(namespaceA)));
        assertThat(stats3.count, is(equalTo(0)));
        assertThat(stats3.map, is(equalTo(null)));
    }

}
