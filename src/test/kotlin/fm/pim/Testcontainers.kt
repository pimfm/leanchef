package fm.pim

import app.cash.sqldelight.driver.jdbc.asJdbcDriver
import com.zaxxer.hikari.HikariDataSource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.utility.DockerImageName

object Testcontainers {
    val postgres by lazy {
        val container = PostgreSQLContainer(DockerImageName.parse("postgres:9.6.12"))

        container
            .apply { start() }
            .let {
                HikariDataSource().apply {
                    jdbcUrl = it.jdbcUrl
                    username = it.username
                    password = it.password
                    driverClassName = it.driverClassName
                }
            }
            .let { Database(it.asJdbcDriver()) }
    }
}