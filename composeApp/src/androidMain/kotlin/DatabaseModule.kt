import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import data.db.CurioteDatabase
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual val databaseModule = module {
    single {
        val dbFile = androidContext().getDatabasePath("curiote.db")
        Room.databaseBuilder<CurioteDatabase>(
            context = androidContext().applicationContext,
            name = dbFile.absolutePath
        )
            .fallbackToDestructiveMigration(true)
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }
    single { get<CurioteDatabase>().curioteDao() }
    single { get<CurioteDatabase>().curioteLinkDao() }
}