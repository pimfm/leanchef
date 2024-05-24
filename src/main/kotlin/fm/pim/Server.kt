
package fm.pim

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*

fun main() {
    embeddedServer(Netty, 8080, "0.0.0.0", module = Application::module).start(wait = true)
}

fun Application.module() {
    configureHTTP()
    configureRouting()
}

fun Application.configureHTTP() {
    install(CORS) { anyHost() }
    install(ContentNegotiation) { json() }
}