package org.ranapat.instancefactory.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.ranapat.instancefactory.InstanceFactory
import org.ranapat.instancefactory.get
import org.ranapat.instancefactory.inject
import org.ranapat.instancefactory.lazyGet
import org.ranapat.instancefactory.lazyInject

class MainActivity : AppCompatActivity() {
    private val instanceH1: ClassH by lazyInject()
    private val instanceH2: ClassH = inject()

    private val classJ5: ClassJ by lazyInject("passedByLazy", ClassJ.ClassJA())
    private val classK5: ClassK by lazyGet("passedByLazy", ClassK.ClassKA())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        examplesA()
        examplesB()
        examplesC()
        examplesD()
        examplesE()
        examplesF()
        examplesG()
        examplesH()
        examplesJ()
        examplesK()
    }

    private fun examplesA() {
        val instanceA1 = InstanceFactory.get(ClassA::class.java)
        instanceA1.explain()
        val instanceA2 = InstanceFactory.get(ClassA::class.java)
        instanceA2.explain()
        InstanceFactory.remove(ClassA::class.java)
        val instanceA3 = ClassA()
        InstanceFactory.set(instanceA3, ClassA::class.java)
        val instanceA3_1 = InstanceFactory.get(ClassA::class.java)
        instanceA3_1.explain()
        val instanceA3_2 = InstanceFactory.get(ClassA::class.java)
        instanceA3_2.explain()
    }

    private fun examplesB() {
        val classB1 = InstanceFactory.get(ClassB::class.java)
        classB1.explain()
        val classB2 = InstanceFactory.get(ClassB::class.java)
        classB2.explain()
    }

    private fun examplesC() {
        val classC1 = InstanceFactory.get(
            ClassC::class.java, arrayOf<Class<*>>(
                String::class.java
            ), "12"
        )
        classC1.explain()
        val classC2 = InstanceFactory.get(
            ClassC::class.java, arrayOf<Class<*>>(
                String::class.java
            ), "12"
        )
        classC2.explain()
    }

    private fun examplesD() {
        val classD1 = InstanceFactory.get(
            ClassD::class.java, arrayOf<Class<*>>(
                String::class.java
            ), "something"
        )
        classD1.explain()
        val classD2 = InstanceFactory.get(
            ClassD::class.java, arrayOf<Class<*>>(
                String::class.java
            ), "something"
        )
        classD2.explain()
    }

    private fun examplesE() {
        val classE1 = InstanceFactory.get(ClassE::class.java)
        classE1.explain()
        val classE2 = InstanceFactory.get(ClassE::class.java)
        classE2.explain()
        InstanceFactory.inject(classE1)
        InstanceFactory.inject(classE2)
        classE1.explain()
        classE2.explain()
    }

    private fun examplesF() {
        val classF1 = InstanceFactory.get(ClassF::class.java)
        classF1.explain()
        val classF2 = InstanceFactory.get(ClassF::class.java)
        classF2.explain()
    }

    private fun examplesG() {
        val classG1 = InstanceFactory.get(ClassG::class.java)
        classG1.explain()
        val classG2 = InstanceFactory.get(ClassG::class.java)
        classG2.explain()
    }

    private fun examplesH() {
        instanceH1.test()
        instanceH2.test()
    }

    private fun examplesJ() {
        val classJ1 = inject<ClassJ>()
        val classJ2:ClassJ = inject()
        val classJ3:ClassJ = inject("passed")
        val classJ4:ClassJ = inject("passed", ClassJ.ClassJA())

        classJ1.test()
        classJ2.test()
        classJ3.test()
        classJ4.test()
        classJ5.test()
    }

    private fun examplesK() {
        val classK1 = get<ClassK>()
        val classK2:ClassK = get()
        val classK3:ClassK = get("passed")
        val classK4:ClassK = get("passed", ClassK.ClassKA())

        classK1.test()
        classK2.test()
        classK3.test()
        classK4.test()
        classK5.test()
    }
}