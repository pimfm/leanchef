package fm.pim

import fm.pim.domain.*
import fm.pim.models.*
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
        is CompleteStep1 -> step1events(command.ovenTemperature, command.waterUsed)
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
    // Step 1
    is BataFishPattedDry -> recipe.copy(usedIngredients = recipe.usedIngredients + "Basa Fillets")
    is VegetablesCut -> recipe.copy(usedIngredients = recipe.usedIngredients + "Green Pepper")
    is Step1Completed -> recipe.copy(completedStep = 1)

    // Step 2
    is AddedToSmallBowl -> recipe.copy(usedIngredients = recipe.usedIngredients + listOf("Mexican Style Spice Mix", "Breadcrumbs", "Oil for the Breadcrumbs"))
    is Step2Completed -> recipe.copy(completedStep = 2)
    is AddedToMediumBowl -> recipe.copy(usedIngredients = recipe.usedIngredients + "Mayonnaise")

    // Step 3
    is Step3Completed -> recipe.copy(completedStep = 3)

    // Step 4
    is IngredientsMixed -> recipe.copy(usedIngredients = recipe.usedIngredients + listOf("Medium Tomato", "Coriander", "Olive Oil for the Salsa", "Chipotle Paste"))
    is Step4Completed -> recipe.copy(completedStep = 4)

    // Step 5
    is TortillasAddedToMicrowave -> recipe.copy(usedIngredients = recipe.usedIngredients + listOf("Plain Taco Tortillas"))
    is Step5Completed -> recipe.copy(completedStep = 5)

    // Step 6
    is Step6Completed -> recipe.copy(completedStep = 6)
    else -> recipe
}

/**
 * (State, Event) -> State
 * Kitchen
 */
fun evolve(kitchen: Kitchen, event: CookingEvent): Kitchen = when (event) {
    // Step 1
    is OvenPreheated            -> Kitchen.oven.modify(kitchen) { it.copy(temperature = event.temperature, state = "on") }
    is HandsWashed              -> Kitchen.waterFaucet.waterUsedInMl.modify(kitchen) { it + event.waterUsed }
    is EquipmentWashed          -> Kitchen.waterFaucet.waterUsedInMl.modify(kitchen) { it + event.waterUsed }
    is BataFishPattedDry        -> Kitchen.garbageBin.filledPercentage.modify(kitchen) { it + 4 }
    is CutOffVegetableDiscarded -> Kitchen.garbageBin.filledPercentage.modify(kitchen) { it + 15 }

    // Step 2
    is PlacedOnBakingTray -> Kitchen.oven.content.modify(kitchen) { "Basa fillets and sliced green peppers" }

    // Step 6
    is Step6Completed -> Kitchen.oven.modify(kitchen) { it.copy(state = "off", content = "", temperature = 176) }

    else -> kitchen
}

// val kitchen = kitchen.waterFaucet.waterUsedInMl += 204