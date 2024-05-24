@file:Suppress("UnusedReceiverParameter", "KotlinConstantConditions")

package fm.pim

import fm.pim.domain.*
import io.ktor.http.*
import io.ktor.server.application.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import java.lang.Exception
import kotlin.time.Duration.Companion.minutes

/**
 * Extension methods for code expressiveness
 */
suspend fun store(newEvents: Flow<CookingEvent>) { events += newEvents.toList() }
val Parameters.step
    get() = this["step"]?.toInt() ?: 0

// Imagine this data was sent from the web application and deserialized based on type
fun ApplicationCall.simulateDeserialization(step: Int) = when (step) {
    1 -> CompleteStep1(ovenTemperature = 220, waterUsed = 440)
    2 -> CompleteStep2(saltAddedInGrams = 3)
    3 -> CompleteStep3(timeInOven = 18.minutes)
    4 -> CompleteStep4(waterUsedInMl = 220)
    5 -> CompleteStep5(method = "Microwave")
    6 -> CompleteStep6(numberOfPeopleServed = 4)
    else -> throw UnsupportedStepException()
}

class UnsupportedStepException : Exception()