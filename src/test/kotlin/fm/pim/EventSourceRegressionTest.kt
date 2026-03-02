package fm.pim

import fm.pim.domain.CookingEvent
import fm.pim.models.Kitchen
import fm.pim.models.Recipe
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.statement.bodyAsText
import io.ktor.server.testing.testApplication
import kotlinx.serialization.json.Json
import java.io.File
import kotlin.collections.emptyList
import kotlin.test.Test
import kotlin.test.assertEquals

class EventSourceRegressionTest {
    @Test
    fun `Refactoring keeps the event sourcing functionality consistent`() = testApplication {
        application {
            module()
        }

        // Arrange
        val eventsBefore: List<CookingEvent>  = client.get("/events").body()
        val kitchenBefore: Kitchen = client.get("/kitchen").body()
        val recipeBefore: Recipe = client.get("/recipes").body()

        // Assert
        assertEquals(emptyList(), eventsBefore)
        assertEquals(Kitchen(), kitchenBefore)
        assertEquals(Recipe(), recipeBefore)

        // Act
        client.post("complete-step/1")
        client.post("complete-step/2")
        client.post("complete-step/3")
        client.post("complete-step/4")

        // Arrange
        val eventsAfter4: List<CookingEvent>  = client.get("/events").body()
        val kitchenAfter4: Kitchen = client.get("/kitchen").body()
        val recipeAfter4: Recipe = client.get("/recipes").body()

        // Assert
        assertEquals(deserializeTo<List<CookingEvent>>("data/events-after-step4.json"), eventsAfter4)
        assertEquals(deserializeTo<Kitchen>("data/kitchen-after-step4.json"), kitchenAfter4)
        assertEquals(deserializeTo<Recipe>("data/recipe-after-step4.json"), recipeAfter4)

        // Act
        client.post("complete-step/5")
        client.post("complete-step/6")

        // Assert
        val eventsAfter6: List<CookingEvent>  = client.get("/events").body()
        val kitchenAfter6: Kitchen = client.get("/kitchen").body()
        val recipeAfter6: Recipe = client.get("/recipes").body()

        assertEquals(deserializeTo<List<CookingEvent>>("data/events-after-step6.json"), eventsAfter6)
        assertEquals(deserializeTo<Kitchen>("data/kitchen-after-step6.json"), kitchenAfter6)
        assertEquals(deserializeTo<Recipe>("data/recipe-after-step6.json"), recipeAfter6)
    }
}

inline fun <reified T> deserializeTo(filePath: String): T {
    return Json.decodeFromString(File(filePath).readText())
}