import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import screens.TabScreen
import theme.*
import javax.swing.UIManager

private val LightColorPalette = lightColors(
    primary = primaryOrange,
    primaryVariant = gridLineColorLight,
    secondary = primaryCharcoal,
    secondaryVariant = lightGrey,
    background =lightGrey,
    surface = lightGrey,
    error = Color.Red,
    onPrimary = accentAmber,
    onSecondary = textColorDark,
    onBackground = lightGrey,
    onSurface = textColorDark,
)

@Composable
@Preview
fun App(size: DpSize) {
    MaterialTheme (colors = LightColorPalette){
        TabScreen()
    }
}

fun main() = application {
    Thread.setDefaultUncaughtExceptionHandler { _, throwable ->
    }
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
    val size by remember { mutableStateOf(DpSize.Unspecified) }

    val windowState = rememberWindowState(/*size = size*/)
    Window(onCloseRequest = ::exitApplication, state = windowState) {

        App(size)
    }
}
