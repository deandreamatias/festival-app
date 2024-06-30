import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import festival.deandreamatias.com.ui.show.ShowsScreen
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext

@Composable
@Preview
fun App() {
    KoinContext {
        MaterialTheme(
            colorScheme = MaterialTheme.colorScheme.copy(
                primary = Color(0xFF6200EE),
                secondary = Color(0xFF03DAC6)
            ),
        ) {
            ShowsScreen()
        }
    }
}
