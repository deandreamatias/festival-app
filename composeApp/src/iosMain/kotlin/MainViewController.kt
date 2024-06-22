import androidx.compose.ui.window.ComposeUIViewController
import festival.deandreamatias.com.initKoin

fun MainViewController() = ComposeUIViewController(configure = { initKoin() }) { App() }