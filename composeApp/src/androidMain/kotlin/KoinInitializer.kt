import android.content.Context
import di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext

actual class KoinInitializer(
    private val context: Context
) {
    actual fun init() {
        GlobalContext.startKoin {
            androidLogger()
            androidContext(context)
            modules(appModule, viewModelModule, databaseModule)
        }
    }
}