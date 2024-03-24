package org.ranapat.instancefactory.example

import android.util.Log

class ClassM(
    private val name: String,
    private val complex: ClassMA?
) {
    class ClassMA {
        override fun toString(): String {
            return "Instance of ClassMA"
        }
    }
    constructor() : this("undefined", null) {
        Log.d("### DEBUG ###", "Class M constructor without passed value for name and complex")
    }
    constructor(name: String) : this(name, null) {
        Log.d("### DEBUG ###", "Class M constructor without passed value for name and complex")
    }

    fun test() {
        Log.d("### DEBUG ###", "Class M test $name ${complex ?: "undefined"}")
    }
}