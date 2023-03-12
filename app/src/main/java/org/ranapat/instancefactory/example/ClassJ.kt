package org.ranapat.instancefactory.example

import android.util.Log

class ClassJ(
    private val name: String,
    private val complex: ClassJA?
) {
    class ClassJA {
        override fun toString(): String {
            return "Instance of ClassJA"
        }
    }
    constructor() : this("undefined", null) {
        Log.d("### DEBUG ###", "Class J constructor without passed value for name and complex")
    }
    constructor(name: String) : this(name, null) {
        Log.d("### DEBUG ###", "Class J constructor without passed value for name and complex")
    }

    fun test() {
        Log.d("### DEBUG ###", "Class J test $name ${complex ?: "undefined"}")
    }
}
