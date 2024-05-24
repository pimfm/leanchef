package fm.pim.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.serialization.Serializable
import kotlin.time.Duration

/**
 * E - Events
 */
@Serializable
sealed interface CookingEvent

// Step 1
@Serializable data class OvenPreheated(val temperature: Int) : CookingEvent
@Serializable data object BataFishPattedDry : CookingEvent
@Serializable data object BataFishSliced : CookingEvent
@Serializable data class HandsWashed(val waterUsed: Int) : CookingEvent
@Serializable data class EquipmentWashed(val waterUsed: Int) : CookingEvent
@Serializable data object VegetablesCut : CookingEvent
@Serializable data object CutOffVegetableDiscarded : CookingEvent
@Serializable data object Step1Completed : CookingEvent

fun step1events(ovenTemperature: Int, waterUsed: Int): Flow<CookingEvent> = flowOf(
    OvenPreheated(ovenTemperature),
    BataFishPattedDry,
    BataFishSliced,
    HandsWashed(waterUsed),
    EquipmentWashed(waterUsed),
    VegetablesCut,
    CutOffVegetableDiscarded,
    Step1Completed
)

// Step 2
@Serializable data object AddedToSmallBowl : CookingEvent
@Serializable data class SeasoningAdded(val saltAdded: Int) : CookingEvent
@Serializable data object AddedToMediumBowl : CookingEvent
@Serializable data object Step2Completed : CookingEvent

fun step2events(saltAddedInGrams: Int): Flow<CookingEvent> = flowOf(
    AddedToSmallBowl,
    SeasoningAdded(saltAddedInGrams),
    AddedToMediumBowl,
    Step2Completed
)

// Step 3
@Serializable data object PlacedOnBakingTray : CookingEvent
@Serializable data object OliveOilDrizzled : CookingEvent
@Serializable data class BakedInOven(val time: Duration) : CookingEvent
@Serializable data object Step3Completed : CookingEvent

fun step3events(timeInOven: Duration): Flow<CookingEvent> = flowOf(
    PlacedOnBakingTray,
    OliveOilDrizzled,
    BakedInOven(timeInOven),
    Step3Completed
)

// Step 4
@Serializable data class BowlCleaned(val waterUsed: Int) : CookingEvent
@Serializable data object HerbsChopped : CookingEvent
@Serializable data object IngredientsMixed : CookingEvent
@Serializable data object Step4Completed : CookingEvent

fun step4events(waterUsed: Int): Flow<CookingEvent> = flowOf(
    BowlCleaned(waterUsed),
    HerbsChopped,
    IngredientsMixed,
    Step4Completed
)

// Step 5
@Serializable data object TortillasAddedToMicrowave : CookingEvent
@Serializable data class TortillasHeated(val method: String) : CookingEvent
@Serializable data object TortillasAddedToOven : CookingEvent
@Serializable data object Step5Completed : CookingEvent


fun step5events(method: String): Flow<CookingEvent> = flowOf(
    TortillasAddedToMicrowave,
    TortillasHeated(method),
    TortillasAddedToOven,
    Step5Completed
)

// Step 6
@Serializable data object SpreadAddedToTortilla : CookingEvent
@Serializable data object ToppingAddedToTortilla : CookingEvent
@Serializable data class RecipeServedToFriendsAndFamily(val numberOfPeopleServed: Int) : CookingEvent
@Serializable data object Step6Completed : CookingEvent

fun step6events(numberOfPeopleServed: Int): Flow<CookingEvent> = flowOf(
    SpreadAddedToTortilla,
    ToppingAddedToTortilla,
    RecipeServedToFriendsAndFamily(numberOfPeopleServed),
    Step6Completed
)