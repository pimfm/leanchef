package fm.pim

// E - Events
sealed interface CookingEvent

// Step 1
data class OvenPreheatedEvent(val temperature: Int) : CookingEvent
data class FishPattedDryEvent(val paperTowelsUsed: Int) : CookingEvent
data object HandsWashedEvent : CookingEvent
data class VegetablesCutEvent(val vegetables: List<Ingredient>) : CookingEvent
data object Step1CompletedEvent : CookingEvent

// Step 2

data object Step2CompletedEvent : CookingEvent

// Step 3
data object Step3CompletedEvent : CookingEvent

// Step 4
data object Step4CompletedEvent : CookingEvent

// Step 5
data object Step5CompletedEvent : CookingEvent

// Step 6
data object Step6CompletedEvent : CookingEvent
