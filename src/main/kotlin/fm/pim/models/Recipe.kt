package fm.pim.models

import arrow.optics.optics
import kotlinx.serialization.Serializable

/**
 * S - State
 */
@optics
@Serializable
data class Recipe(
    val usedIngredients: List<String> = emptyList(),
    val usedUtensils: List<String> = emptyList(),
    val completedStep: Int = 0
) {
    companion object
}