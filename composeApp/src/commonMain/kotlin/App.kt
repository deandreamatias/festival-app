import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import festival.deandreamatias.com.ui.ShowsScreen
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import festival_app.composeapp.generated.resources.Res
import festival_app.composeapp.generated.resources.compose_multiplatform
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