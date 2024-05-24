package fm.pim

import fm.pim.domain.*
import fm.pim.models.Kitchen
import fm.pim.models.Recipe
import fm.pim.models.oven
import fm.pim.models.temperature
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

/**
 * (Command, State) -> Flow<Event>
 */
fun decide(command: CompleteRecipeStep, recipe: Recipe): Flow<CookingEvent> {
    // Place for guards and validation
    assert(recipe.completedStep == command.step - 1)

    // Once validated, return new events as a result of command completion
    return when (command) {
        is CompleteStep1 -> step1events(command.ovenTemperature)
        is CompleteStep2 -> step2events(command.saltAddedInGrams)
        is CompleteStep3 -> step3events(command.timeInOven)
        is CompleteStep4 -> step4events(command.waterUsedInMl)
        is CompleteStep5 -> step5events(command.method)
        is CompleteStep6 -> step6events(command.numberOfPeopleServed)
        else -> emptyFlow()
    }
}

/**
 * (State, Event) -> State
 * Recipe
 */
fun evolve(recipe: Recipe, event: CookingEvent): Recipe = when (event) {

    else -> recipe
}

/**
 * (State, Event) -> State
 * Kitchen
 */
fun evolve(kitchen: Kitchen, event: CookingEvent): Kitchen = when (event) {
    is OvenPreheated -> Kitchen.oven.temperature.modify(kitchen) { event.temperature }
    else -> TODO()
}