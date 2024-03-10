package org.ranapat.instancefactory.example

import android.util.Log

class ClassL(
    private val name: String,
    private val complex: ClassLA?
) {
    class ClassLA {
        override fun toString(): String {
            return "Instance of ClassLA"
        }
    }
    constructor() : this("undefined", null) {
        Log.d("### DEBUG ###", "Class L constructor without passed value for name and complex")
    }
    constructor(name: String) : this(name, null) {
        Log.d("### DEBUG ###", "Class L constructor without passed value for name and complex")
    }

    fun test() {
        Log.d("### DEBUG ###", "Class L test $name ${complex ?: "undefined"}")
    }
}