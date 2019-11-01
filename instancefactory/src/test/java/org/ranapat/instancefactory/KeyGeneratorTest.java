package org.ranapat.instancefactory;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class KeyGeneratorTest {

    @Test
    public void shouldGenerateCase1() {
        assertThat(KeyGenerator.generate(String.class, new Class[]{Integer.class}, 1), is(equalTo("java.lang.String-java.lang.Integer:1")));
    }

    @Test
    public void shouldGenerateCase2() {
        assertThat(KeyGenerator.generate(String.class, new Class[]{String.class}, "value"), is(equalTo("java.lang.String-java.lang.String:value")));
    }

    @Test
    public void shouldGenerateCase3() {
        assertThat(KeyGenerator.generate(String.class, new Class[]{String.class, Integer.class}, "value", 1), is(equalTo("java.lang.String-java.lang.String:value,java.lang.Integer:1")));
    }

    @Test
    public void shouldGenerateCase4() {
        assertThat(KeyGenerator.generate(String.class, new Class[]{}), is(equalTo("java.lang.String")));
    }

    @Test
    public void shouldFailCase1() {
        assertThat(KeyGenerator.generate(String.class, new Class[]{String.class}, 1), is(equalTo(null)));
    }

    @Test
    public void shouldFailCase2() {
        assertThat(KeyGenerator.generate(String.class, new Class[]{String.class}, "value", "anothervalue"), is(equalTo(null)));
    }

}