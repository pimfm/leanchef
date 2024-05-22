package fm.pim

import com.fraktalio.fmodel.domain.IDecider
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf

class RecipeDecider : IDecider<CompleteRecipeStep, Kitchen, CookingEvent> {
    override val decide = ::decideRecipeStepCompletion
    override val evolve = ::evolveKitchenToCurrentCookingState
    override val initialState = initialOrganisedAndCleanKitchen()
}

fun decideRecipeStepCompletion(command: CompleteRecipeStep, state: Kitchen) = when (command.number) {
    1 -> completeStep1()
    2 -> completeStep2()
    3 -> completeStep3()
    4 -> completeStep4()
    5 -> completeStep5()
    6 -> completeStep6()
    else -> emptyFlow()
}

fun evolveKitchenToCurrentCookingState(state: Kitchen, event: CookingEvent): Kitchen = when (event) {
    is FishPattedDryEvent -> TODO()
    HandsWashedEvent -> TODO()
    is OvenPreheatedEvent -> TODO()
    is VegetablesCutEvent -> TODO()

    is Step1CompletedEvent -> TODO()
    is Step2CompletedEvent -> TODO()
    is Step3CompletedEvent -> TODO()
    is Step4CompletedEvent -> TODO()
    is Step5CompletedEvent -> TODO()
    is Step6CompletedEvent -> TODO()
}

fun initialOrganisedAndCleanKitchen() = Kitchen(
    ingredients = listOf(
        Ingredient("Basa Fillets"),
        Ingredient("Green Pepper"),
        Ingredient("Mexican Style Spice Mix"),
        Ingredient("Breadcrumbs"),
        Ingredient("Mayonnaise"),
        Ingredient("Medium Tomato"),
        Ingredient("Coriander"),
        Ingredient("Chipotle Paste"),
        Ingredient("Plain Taco Tortillas"),
        Ingredient("Soured Cream"),
        Ingredient("Oil for the Breadcrumbs"),
        Ingredient("Olive Oil for the Salsa")
    ),
    utensils = listOf(
        Utensil("Paper towels"),
        Utensil("Bowl"),
        Utensil("Baking sheets"),
        Utensil("Baking tray")
    ),
    oven = Oven(type = "Electric", status = "off"),
)

fun completeStep1() = flowOf(
    OvenPreheatedEvent(temperature = 220),
    FishPattedDryEvent(paperTowelsUsed = 2),
    HandsWashedEvent,
    VegetablesCutEvent(emptyList()),
    Step1CompletedEvent
)

fun completeStep2() = flowOf(
    Step2CompletedEvent
)

fun completeStep3() = flowOf(
    Step3CompletedEvent
)

fun completeStep4() = flowOf<CookingEvent>()
fun completeStep5() = flowOf<CookingEvent>()
fun completeStep6() = flowOf<CookingEvent>()