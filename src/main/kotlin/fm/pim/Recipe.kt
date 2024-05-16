data class Recipe(
    val createdAt: String,
    val description: String,
    val difficulty: Int,
    val headline: String,
    val id: String,
    val imagePath: String,
    val ingredients: List<Ingredient>,
    val label: Label,
    val link: String,
    val name: String,
    val nutrition: List<Nutrition>,
    val prepTime: String,
    val servingSize: Int,
    val slug: String,
    val steps: List<Step>,
    val tags: List<Tag>,
    val totalTime: String,
    val uniqueRecipeCode: String,
    val updatedAt: String,
    val utensils: List<Utensil>,
    val uuid: String,
    val websiteUrl: String
) {
    data class Ingredient(
        val id: String,
        val imagePath: String,
        val name: String,
        val slug: String,
        val type: String,
        val uuid: String
    )

    data class Label(
        val backgroundColor: String,
        val foregroundColor: String,
        val text: String
    )

    data class Nutrition(
        val amount: Double,
        val name: String,
        val type: String,
        val unit: String
    )

    data class Step(
        val images: List<Image>,
        val index: Int,
        val instructions: String
    ) {
        data class Image(
            val caption: String,
            val path: String
        )
    }

    data class Tag(
        val id: String,
        val name: String,
        val slug: String
    )

    data class Utensil(
        val id: String,
        val name: String
    )
}