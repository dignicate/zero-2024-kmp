import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import org.jetbrains.compose.resources.painterResource
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dignicate.zero_2024_kmp.ui.automobile.AutomobileCompanyListScreen
import com.dignicate.zero_2024_kmp.util.logger
import zero2024kmp.composeapp.generated.resources.Res
import zero2024kmp.composeapp.generated.resources.compose_multiplatform

private val LightColorPalette = lightColors(
    primary = Color(0xFF6200EE),
    secondary = Color(0xFF03DAC5),
    background = Color.White,
)

private val DarkColorPalette = darkColors(
    primary = Color(0xFFBB86FC),
    secondary = Color(0xFF03DAC5),
    background = Color.Black,
)

@Composable
fun MyCustomTheme(content: @Composable () -> Unit) {
    val colors = if (isSystemInDarkTheme()) {
        logger.d("DarkColorPalette")
        DarkColorPalette
    } else {
        logger.d("LightColorPalette")
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        content = content
    )
}


@Composable
fun App() {
    initLogger()
    initKoin()
    MyCustomTheme {
        AutomobileCompanyListScreen(
            modifier = Modifier,
        )
    }
}

@Composable
private fun Demo() {
    // Project 作成時に元々存在していたものをこちらに移動
    var showContent by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .padding(top = 48.dp)
            .fillMaxWidth()
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = { showContent = !showContent },
        ) {
            Text("Click me!")
        }
        AnimatedVisibility(showContent) {
            val greeting = remember { Greeting().greet() }
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painterResource(Res.drawable.compose_multiplatform), null)
                Text("Compose: $greeting")
            }
        }
    }
}
