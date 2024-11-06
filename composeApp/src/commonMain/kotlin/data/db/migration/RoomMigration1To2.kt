package data.db.migration

import androidx.room.migration.Migration
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL

class RoomMigration1To2(from: Int = 1, to: Int = 2) : Migration(from, to) {

    override fun migrate(connection: SQLiteConnection) {
        connection.execSQL("CREATE TABLE IF NOT EXISTS curiote_categories (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL)".trimIndent())
        connection.execSQL("CREATE TABLE IF NOT EXISTS curiote_category_combined (curioteId INTEGER NOT NULL, curioteCategoryId INTEGER NOT NULL, PRIMARY KEY(curioteId, curioteCategoryId))".trimIndent())
        connection.execSQL("ALTER TABLE curiotes ADD COLUMN priority INTEGER NOT NULL DEFAULT 0'")
    }
}