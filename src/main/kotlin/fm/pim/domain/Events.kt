package fm.pim.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlin.time.Duration

/**
 * E - Events
 */
sealed interface CookingEvent

// Step 1
data class OvenPreheated(val temperature: Int) : CookingEvent
data object BataFishPattedDry : CookingEvent
data object BataFishSliced : CookingEvent
data object HandsWashed : CookingEvent
data object EquipmentWashed : CookingEvent
data object VegetablesCut : CookingEvent
data object CutOffVegetableDiscarded : CookingEvent

fun step1events(ovenTemperature: Int): Flow<CookingEvent> = flowOf(
    OvenPreheated(ovenTemperature),
    BataFishPattedDry,
    BataFishSliced,
    HandsWashed,
    EquipmentWashed,
    VegetablesCut,
    CutOffVegetableDiscarded
)

// Step 2
data object AddedToSmallBowl : CookingEvent
data class SeasoningAdded(val saltAdded: Int) : CookingEvent
data object AddedToMediumBowl : CookingEvent

fun step2events(saltAddedInGrams: Int): Flow<CookingEvent> = flowOf(
    AddedToSmallBowl,
    SeasoningAdded(saltAddedInGrams),
    AddedToMediumBowl
)

// Step 3
data object PlacedOnBakingTray : CookingEvent
data object OliveOilDrizzled : CookingEvent
data class BakedInOven(val time: Duration) : CookingEvent

fun step3events(timeInOven: Duration): Flow<CookingEvent> = flowOf(
    PlacedOnBakingTray,
    OliveOilDrizzled,
    BakedInOven(timeInOven)
)

// Step 4
data class BowlCleaned(val waterUsed: Int) : CookingEvent
data object HerbsChopped : CookingEvent
data object IngredientsMixed : CookingEvent

fun step4events(waterUsed: Int): Flow<CookingEvent> = flowOf(
    BowlCleaned(waterUsed),
    HerbsChopped,
    IngredientsMixed
)

// Step 5
data object TortillasAddedToMicrowave : CookingEvent
data class TortillasHeated(val method: String) : CookingEvent
data object TortillasAddedToOven : CookingEvent

fun step5events(method: String): Flow<CookingEvent> = flowOf(
    TortillasAddedToMicrowave,
    TortillasHeated(method),
    TortillasAddedToOven
)

// Step 6
data object SpreadAddedToTortilla : CookingEvent
data object ToppingAddedToTortilla : CookingEvent
data class RecipeServedToFriendsAndFamily(val numberOfPeopleServed: Int) : CookingEvent

fun step6events(numberOfPeopleServed: Int): Flow<CookingEvent> = flowOf(
    SpreadAddedToTortilla,
    ToppingAddedToTortilla,
    RecipeServedToFriendsAndFamily(numberOfPeopleServed)
)