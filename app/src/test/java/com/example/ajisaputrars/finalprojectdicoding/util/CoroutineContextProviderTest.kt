package com.example.ajisaputrars.finalprojectdicoding.util

import kotlinx.coroutines.experimental.Unconfined
import org.junit.Assert.*
import kotlin.coroutines.experimental.CoroutineContext

class CoroutineContextProviderTest  : CoroutineContextProvider() {

    override val main: CoroutineContext = Unconfined

}