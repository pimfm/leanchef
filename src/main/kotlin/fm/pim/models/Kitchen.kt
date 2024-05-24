package fm.pim.models

import arrow.optics.optics

/**
 * S - State
 */
@optics
data class Kitchen(
    val oven: Oven = Oven(),
    val garbageBin: GarbageBin = GarbageBin()
) {
    companion object
}

@optics
data class Oven(
    val state: String = "off",
    val temperature: Int = 0
) {
    companion object
}

@optics
data class GarbageBin(
    val filledPercentage: Int = 0
) {
    companion object
}