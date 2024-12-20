import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import data.db.CurioteDatabase
import org.koin.dsl.module
import data.db.instantiateImpl
import platform.Foundation.NSHomeDirectory

actual val databaseModule = module {
    single {
        val dbFile = NSHomeDirectory() + "/curiote.db"
        Room.databaseBuilder<CurioteDatabase>(
            name = dbFile,
            factory = { CurioteDatabase::class.instantiateImpl() }
        )
            .setDriver(BundledSQLiteDriver())
            .build()
    }
    single { get<CurioteDatabase>().curioteDao() }
}
