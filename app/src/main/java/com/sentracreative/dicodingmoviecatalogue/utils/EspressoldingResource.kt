package com.sentracreative.dicodingmoviecatalogue.utils

import androidx.test.espresso.idling.CountingIdlingResource

object EspressoldingResource {
    private const val RESOURCE = "GLOBAL"
    val espressoTestIdlingResource = CountingIdlingResource(RESOURCE)

    fun increment() {
        espressoTestIdlingResource.increment()
    }

    fun decrement() {
        espressoTestIdlingResource.decrement()
    }

}