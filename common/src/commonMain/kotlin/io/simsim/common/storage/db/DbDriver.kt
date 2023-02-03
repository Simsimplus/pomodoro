package io.simsim.common.storage.db

import app.cash.sqldelight.db.SqlDriver
import io.simsim.Database

expect class DriverFactory {
    fun createDriver(): SqlDriver
}

fun createDatabase(driverFactory: DriverFactory): Database {
    val driver = driverFactory.createDriver()
    val database = Database(driver)

    // Do more work with the database (see below).
    return database
}

expect fun getDatabase(): Database

