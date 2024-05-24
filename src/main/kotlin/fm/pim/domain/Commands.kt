package fm.pim.domain

import kotlin.time.Duration

/**
 * C - Commands
 */
abstract class CompleteRecipeStep(val step: Int)

data class CompleteStep1(val ovenTemperature: Int, val waterUsed: Int) : CompleteRecipeStep(step = 1)
data class CompleteStep2(val saltAddedInGrams: Int) : CompleteRecipeStep(step = 2)
data class CompleteStep3(val timeInOven: Duration) : CompleteRecipeStep(step = 3)
data class CompleteStep4(val waterUsedInMl: Int) : CompleteRecipeStep(step = 4)
data class CompleteStep5(val method: String) : CompleteRecipeStep(step = 5)
data class CompleteStep6(val numberOfPeopleServed: Int) : CompleteRecipeStep(step = 6)
