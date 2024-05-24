@file:Suppress("KotlinConstantConditions")

package fm.pim.models

import fm.pim.domain.BataFishSliced
import fm.pim.domain.HandsWashed
import fm.pim.domain.SeasoningAdded
import fm.pim.domain.TortillasHeated
import fm.pim.events

fun generateReport(): String {
    val handsAreWashedIndex = events.indexOfFirst { it is HandsWashed }
    val rawFishHandledIndex = events.indexOfFirst { it is BataFishSliced || it is BataFishSliced }

    val rawFishReport = when {
        rawFishHandledIndex == -1                 -> "No raw fish was handled"
        handsAreWashedIndex == -1                 -> "Hands not washed after handling raw fish"
        handsAreWashedIndex > rawFishHandledIndex -> "Hands where washed after handling raw fish 👍"
        handsAreWashedIndex < rawFishHandledIndex -> "Hands where washed BEFORE handling raw fish!"
        else -> "Unknown"
    }

    return """
        1. Which method was used to heat the tortillas in step 5?
        - ${(events.firstOrNull { it is TortillasHeated } as TortillasHeated?)?.method ?: "Unknown"}
    
        2. How many grams of salt where added in step 2? 
        - ${(events.firstOrNull { it is SeasoningAdded } as SeasoningAdded?)?.saltAdded ?: "0"} grams
        
        3. Did we wash our hands after handling the raw fish in step 1?
        - $rawFishReport
    """
}