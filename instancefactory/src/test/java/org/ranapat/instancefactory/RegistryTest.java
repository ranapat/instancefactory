package org.ranapat.instancefactory;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class RegistryTest {
    private static final Namespace defaultNamespace = Namespace.DEFAULT;
    private static final Namespace namespaceA = new Namespace() {};

    @Test
    public void predefineNamespaces() {
        assertThat(Registry.get(null), is(equalTo(defaultNamespace)));
        assertThat(Registry.get(""), is(equalTo(defaultNamespace)));
        assertThat(Registry.get("anything"), is(equalTo(defaultNamespace)));
        assertThat(Registry.get("a"), is(equalTo(defaultNamespace)));

        Registry.set("a", namespaceA);

        assertThat(Registry.get("a"), is(equalTo(namespaceA)));

        Registry.unset(null);
        Registry.unset("");
        Registry.unset("a");

        assertThat(Registry.get("a"), is(equalTo(defaultNamespace)));
    }

}
