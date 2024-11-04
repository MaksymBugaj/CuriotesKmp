
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import org.koin.compose.KoinContext
import ui.navigation.MainView
import ui.theme.AppTheme

@Composable
fun App() {
    AppTheme {
        KoinContext {
            Surface(
                color = MaterialTheme.colorScheme.surface
            ) {
                MainView()
            }
        }
    }
}