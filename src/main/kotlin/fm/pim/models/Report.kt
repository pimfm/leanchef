@file:Suppress("KotlinConstantConditions")

package fm.pim.models

import fm.pim.domain.*
import fm.pim.events

fun generateReport(): String {
    val step1CompletedIndex = events.indexOfFirst { it is Step1Completed }
    val step2CompletedIndex = events.indexOfFirst { it is Step2Completed }
    val step5CompletedIndex = events.indexOfFirst { it is Step5Completed }
    val handsAreWashedIndex = events.indexOfFirst { it is HandsWashed }
    val rawFishHandledIndex = events.indexOfFirst { it is BataFishSliced || it is BataFishSliced }

    val heatingMethodReport = when {
        step5CompletedIndex == -1 -> "Step 5 is not yet completed"
        else -> (events.firstOrNull { it is TortillasHeated } as TortillasHeated?)?.method ?: "Unknown"
    }

    val saltUsedReport = when {
        step2CompletedIndex == -1 -> "Step 2 is not yet completed"
        else -> ((events.firstOrNull { it is SeasoningAdded } as SeasoningAdded?)?.saltAdded.toString()) + " grams"
    }

    val rawFishReport = when {
        step1CompletedIndex == -1 -> "Step 1 is not yet completed"
        rawFishHandledIndex == -1 -> "No raw fish was handled"
        handsAreWashedIndex == -1 -> "Hands not washed after handling raw fish"
        handsAreWashedIndex > rawFishHandledIndex -> "Hands where washed after handling raw fish 👍"
        handsAreWashedIndex < rawFishHandledIndex -> "Hands where washed BEFORE handling raw fish!"
        else -> "Unknown"
    }

    return """
        1. Which method was used to heat the tortillas in step 5?
        - $heatingMethodReport
    
        2. How many grams of salt where added in step 2? 
        - $saltUsedReport
        
        3. Did we wash our hands after handling the raw fish in step 1?
        - $rawFishReport
    """
}