import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import org.jetbrains.compose.resources.painterResource
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dignicate.zero_2024_kmp.ui.screen.automobile.AutomobileCompanyListScreen
import com.dignicate.zero_2024_kmp.ui.design.MyCustomTheme
import com.dignicate.zero_2024_kmp.ui.design.darkExColors
import com.dignicate.zero_2024_kmp.ui.design.lightExColors
import zero2024kmp.composeapp.generated.resources.Res
import zero2024kmp.composeapp.generated.resources.compose_multiplatform


@Composable
fun App() {
    initLogger()
    initKoin()

    val exColors = if (isSystemInDarkTheme()) {
        darkExColors()
    } else {
        lightExColors()
    }

    MyCustomTheme(
        exColors = exColors,
    ) {
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
