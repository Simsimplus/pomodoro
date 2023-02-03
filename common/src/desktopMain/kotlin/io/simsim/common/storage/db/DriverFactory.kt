package io.simsim.common.storage.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import io.simsim.Database

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        val driver: SqlDriver = JdbcSqliteDriver("jdbc:sqlite:./setting.db")
        Database.Schema.create(driver)
        return driver
    }
}

actual fun getDatabase(): Database = Database(DriverFactory().createDriver())