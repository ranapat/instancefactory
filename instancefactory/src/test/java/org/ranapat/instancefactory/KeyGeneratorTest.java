package org.ranapat.instancefactory;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class KeyGeneratorTest {

    @Test
    public void generateCase1() {
        assertThat(KeyGenerator.generate(String.class, new Class[]{Integer.class}, 1), is(equalTo("java.lang.String-java.lang.Integer:1")));
    }

    @Test
    public void generateCase2() {
        assertThat(KeyGenerator.generate(String.class, new Class[]{String.class}, "value"), is(equalTo("java.lang.String-java.lang.String:value")));
    }

    @Test
    public void generateCase3() {
        assertThat(KeyGenerator.generate(String.class, new Class[]{String.class, Integer.class}, "value", 1), is(equalTo("java.lang.String-java.lang.String:value,java.lang.Integer:1")));
    }

    @Test
    public void generateCase4() {
        assertThat(KeyGenerator.generate(String.class, new Class[]{}), is(equalTo("java.lang.String")));
    }

    @Test
    public void failCase1() {
        assertThat(KeyGenerator.generate(String.class, new Class[]{String.class}, 1), is(equalTo(null)));
    }

    @Test
    public void failCase2() {
        assertThat(KeyGenerator.generate(String.class, new Class[]{String.class}, "value", "anothervalue"), is(equalTo(null)));
    }

}