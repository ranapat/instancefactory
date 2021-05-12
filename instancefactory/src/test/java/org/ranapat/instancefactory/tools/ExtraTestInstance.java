package org.ranapat.instancefactory.tools;

public class ExtraTestInstance extends TestInstance {
    private String extraTest;

    public ExtraTestInstance() {
        super();

        this.extraTest = "extraTest";
    }

    @Override
    public String toString() {
        return "ExtraTestInstance{" +
                "extraTest='" + extraTest + '\'' +
                '}';
    }
}
