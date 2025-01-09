package data.db

import data.db.migration.RoomMigration1To2

object DatabaseHelper {

    const val DATABASE_VERSION = 2
    const val DATABASE_NAME = "Curiotes"

    private val migrations = listOf(
        RoomMigration1To2()
    )

    fun getMigrations() = migrations.toTypedArray()

}