package org.ranapat.instancefactory.tools;

public class TestInstance {
    private String test;

    public TestInstance() {
        this.test = "test";
    }

    @Override
    public String toString() {
        return "TestInstance{" +
                "test='" + test + '\'' +
                '}';
    }
}
