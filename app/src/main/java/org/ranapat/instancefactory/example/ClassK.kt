package org.ranapat.instancefactory.example

import android.util.Log

class ClassK(
    private val name: String,
    private val complex: ClassKA?
) {
    class ClassKA {
        override fun toString(): String {
            return "Instance of ClassKA"
        }
    }
    constructor() : this("undefined", null) {
        Log.d("### DEBUG ###", "Class K constructor without passed value for name and complex")
    }
    constructor(name: String) : this(name, null) {
        Log.d("### DEBUG ###", "Class K constructor without passed value for name and complex")
    }

    fun test() {
        Log.d("### DEBUG ###", "Class K test $name ${complex ?: "undefined"}")
    }
}