package fm.pim

import app.cash.sqldelight.driver.jdbc.asJdbcDriver
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        post("/complete-step/{number}") {
            call.respondText("step ${call.parameters["number"]} completed")
        }
        get("/state") {
            call.respond(listOf("server", "responses"))
        }
        get("/events") {
            HikariDataSource()
                .apply {
                    jdbcUrl = "jdbc:postgres://localhost:3306/postgres"
                    username = "postgres"
                    password = "postgres"
                }
                .asJdbcDriver()
        }
    }
}