package org.ranapat.instancefactory.example;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import org.ranapat.instancefactory.InstanceFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ClassA instanceA1 = InstanceFactory.get(ClassA.class);
        instanceA1.explain();

        final ClassA instanceA2 = InstanceFactory.get(ClassA.class);
        instanceA2.explain();

        InstanceFactory.remove(ClassA.class);

        final ClassA instanceA3 = new ClassA();
        InstanceFactory.set(ClassA.class, instanceA3);

        final ClassA instanceA3_1 = InstanceFactory.get(ClassA.class);
        instanceA3_1.explain();
        final ClassA instanceA3_2 = InstanceFactory.get(ClassA.class);
        instanceA3_2.explain();

        final ClassB classB1 = InstanceFactory.get(ClassB.class);
        classB1.explain();

        final ClassB classB2 = InstanceFactory.get(ClassB.class);
        classB2.explain();
    }
}
