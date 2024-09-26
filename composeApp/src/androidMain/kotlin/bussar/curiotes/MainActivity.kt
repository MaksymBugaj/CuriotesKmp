package bussar.curiotes

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

/**
 * to check for kmp:
 * decompose
 * mutliplatform settings
 * coil/Kamel - loading images
 * kmmbridge?
 * voyager - navigation
 * molecule?
 * material 3 window size class
 * SKIE suspend funs
 * Cinterop
 *
 * for compose multiplatform:
 * kotlin multiplatform wizard
 * precompose
 * accessibility - add test tags
 * basicTextField2 - from 1.7.0
 *
 *
 */