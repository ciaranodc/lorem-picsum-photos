package com.lorempicsum.photos

class TestUtils {
    fun getRawJsonFromFile(filepath: String): String? {
        val inputStream = javaClass.classLoader?.getResourceAsStream(filepath)

        return inputStream?.bufferedReader().use { it?.readText() }
    }
}