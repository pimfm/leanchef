package fm.pim.models

import arrow.optics.optics
import kotlinx.serialization.Serializable

/**
 * S - State
 */
@optics
@Serializable
data class Kitchen(
    val oven: Oven = Oven(),
    val garbageBin: GarbageBin = GarbageBin(),
    val waterFaucet: WaterFaucet = WaterFaucet()
) {
    companion object
}

@optics
@Serializable
data class Oven(
    val state: String = "off",
    val content: String = "",
    val temperature: Int = 0
) {
    companion object
}

@optics
@Serializable
data class GarbageBin(
    val filledPercentage: Int = 0,
) {
    companion object
}

@optics
@Serializable
data class WaterFaucet(
    val waterUsedInMl: Int = 0
) {
    companion object
}