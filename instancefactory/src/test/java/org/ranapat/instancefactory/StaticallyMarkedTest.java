package org.ranapat.instancefactory;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

import org.junit.After;
import org.junit.Test;
import org.ranapat.instancefactory.tools.StaticallyMarkedA;
import org.ranapat.instancefactory.tools.StaticallyMarkedB;

public class StaticallyMarkedTest {

    @After
    public void after() {
        InstanceFactory.clear();
    }

    @Test
    public void staticallyMarkedWithoutParameters() {
        final StaticallyMarkedA staticallyMarked1 = InstanceFactory.get(StaticallyMarkedA.class);
        assertThat(staticallyMarked1, is(not(nullValue())));

        final StaticallyMarkedA staticallyMarked2 = InstanceFactory.get(StaticallyMarkedA.class);
        assertThat(staticallyMarked2, is(equalTo(staticallyMarked2)));
    }

    @Test
    public void staticallyMarkedWithParameters() {
        final StaticallyMarkedB staticallyMarked1 = InstanceFactory.get(StaticallyMarkedB.class, new Class[]{String.class}, "something");
        assertThat(staticallyMarked1, is(not(nullValue())));

        final StaticallyMarkedB staticallyMarked2 = InstanceFactory.get(StaticallyMarkedB.class, new Class[]{String.class}, "something");
        assertThat(staticallyMarked2, is(equalTo(staticallyMarked2)));

        final StaticallyMarkedB staticallyMarked3 = InstanceFactory.get(StaticallyMarkedB.class, new Class[]{String.class}, "somethingElse");
        assertThat(staticallyMarked2, is(not(equalTo(staticallyMarked3))));
    }

}
