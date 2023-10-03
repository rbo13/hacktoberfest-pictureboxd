package core.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import core.theme.DarkText

@Composable
fun MainScaffold(
    title: String? = "",
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "",
                        style = TextStyle(
                            color = DarkText,
                            fontSize = 20.sp
                        )
                    )
                },
                backgroundColor = Color.White,
                elevation = 0.dp,
                modifier = Modifier
                    .height(56.dp)
                    .background(Color.White)
            )
        },
        content = {
            content()
        }
    )
}
