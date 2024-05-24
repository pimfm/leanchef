@file:Suppress("SimpleRedundantLet")

package fm.pim

import fm.pim.domain.CookingEvent
import fm.pim.models.Kitchen
import fm.pim.models.Recipe
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.flow.fold

val events: MutableList<CookingEvent> = mutableListOf()

fun Application.configureRouting() {
    routing {
        post("/complete-step/{step}") {
            val step    = call.parameters.step
            val command = call.simulateDeserialization(step)
            val recipe  = events.fold(Recipe(), ::evolve)

            val evolvedRecipe = decide(command, recipe)
                .also { events -> store(events) }
                .let  { events -> events.fold(recipe, ::evolve) }

            call.respond(evolvedRecipe)
        }

        get("/events") {
            call.respond(events)
        }

        get("/recipe") {
            val recipe = events.fold(Recipe(), ::evolve)

            call.respond(recipe)
        }

        get("/kitchen") {
            val kitchen = events.fold(Kitchen(), ::evolve)

            call.respond(kitchen)
        }

        get("report") {
            call.respondText(generateReport())
        }
    }
}