package com.bangkit.saltiesmovie.core.util

import org.koin.core.KoinComponent
import org.koin.core.inject

object FieldInjection {
    //sumber https://stackoverflow.com/questions/50840373/how-to-inject-dependency-using-koin-in-top-level-function
    inline fun <reified T> injectField(): T {
        return object : KoinComponent {
            val obj: T by inject()
        }.obj
    }
}