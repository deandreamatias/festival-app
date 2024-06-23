import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import festival.deandreamatias.com.ui.ShowsScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

import org.koin.compose.KoinContext

@Composable
@Preview
fun App() {
    KoinContext {
        MaterialTheme {
            ShowsScreen()
        }
    }
}