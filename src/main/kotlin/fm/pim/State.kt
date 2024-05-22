package fm.pim

// S - State
data class Kitchen(
    val ingredients: List<Ingredient> = emptyList(),
    val utensils: List<Utensil> = emptyList(),
    val oven: Oven = Oven(),
    val garbageBin: GarbageBin = GarbageBin()
)

data class Utensil(
    val name: String,
)

data class Oven(
    val type: String = "Electric",
    val temperature: Int = 0,
    val status: String = "Off"
)

data class GarbageBin(
    val filledPercentage: Int = 0
)

data class Ingredient(
    val name: String,
    val status: String = "",
)
