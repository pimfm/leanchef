package fm.pim

import org.junit.Test
import kotlin.test.assertEquals

class RecipeScraperTest {
    @Test
    fun `Can store test recipe in database using SQLDelight and testcontainer`() {
        // Arrange
        val database = Testcontainers.postgres

        // Act
        val recipes = database.recipeQueries.selectAll().executeAsList()

        // Assert
        assertEquals(0, recipes.size)
    }
}
