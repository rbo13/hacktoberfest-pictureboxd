import androidx.compose.ui.window.ComposeUIViewController
import features.login.LoginScreen

actual fun getPlatformName(): String = "iOS"

fun MainViewController() = ComposeUIViewController { LoginScreen() }