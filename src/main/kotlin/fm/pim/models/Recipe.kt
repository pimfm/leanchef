package fm.pim.models

import arrow.optics.optics

/**
 * S - State
 */
@optics
data class Recipe(
    val usedIngredients: List<String> = emptyList(),
    val usedUtensils: List<String> = emptyList(),
    val completedStep: Int = 0
) {
    companion object
}