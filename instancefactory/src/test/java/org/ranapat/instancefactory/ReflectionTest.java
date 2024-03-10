package org.ranapat.instancefactory;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;
import org.ranapat.instancefactory.tools.ClassWithFields1;
import org.ranapat.instancefactory.tools.ClassWithFields2;

import java.lang.reflect.Field;
import java.util.List;

public class ReflectionTest {

    @Test
    public void getAllFields() {
        final List<Field> fields1 = InstanceFactory.getDeclaredFields(ClassWithFields1.class);
        locate(fields1, "java.lang.Integer");
        locate(fields1, "java.lang.String");

        final List<Field> fields2 = InstanceFactory.getDeclaredFields(ClassWithFields2.class);
        locate(fields2, "java.lang.Integer");
        locate(fields2, "java.lang.String");
        locate(fields2, "java.lang.Float");
    }

    private void locate(final List<Field> fields, final String name) {
        boolean located = false;
        for (final Field field : fields) {
            if (field.getType().getName().equals(name)) {
                located = true;
                break;
            }
        }
        assertThat(located, is(equalTo(true)));
    }

}
