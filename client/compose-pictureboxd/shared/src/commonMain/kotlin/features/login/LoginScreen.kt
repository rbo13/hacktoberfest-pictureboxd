package features.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import core.theme.ButtonText
import core.theme.LabelStyle
import core.theme.InputField
import core.theme.InputHint
import core.theme.Primary
import core.theme.Shapes
import core.theme.SubHeader
import core.theme.ClickableText
import core.theme.WhiteSmoke
import core.utils.MainScaffold
import core.utils.keyboardAsState
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory

@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    val isError = false

    val viewModel = getViewModel(Unit, viewModelFactory { LoginViewModel() })
    val state = viewModel.state.collectAsState().value
    val onEvent = viewModel::onEvent
    val isKeyboardOpen by keyboardAsState()
    val paddingBottom = if (isKeyboardOpen) 250.dp else 16.dp

    MainScaffold {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(16.dp))
//                Icon(
//                    imageVector = Icons.Default.MovieFilter,
//                    null,
//                    tint = Primary,
//                    modifier = Modifier.size(65.dp),
//                )
            Text(
                text = "Login",
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 32.sp),
                modifier = Modifier.padding(top = 8.dp, bottom = 24.dp)
            )

            Text(
                text = "Email Address",
                style = LabelStyle
            )
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    onEvent(LoginEvent.EmailChanged(it))
                },
                placeholder = { Text(text = "juan@delacruz.com", style = InputHint) },
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = WhiteSmoke,
                    focusedIndicatorColor = if (state.isEmailError) MaterialTheme.colors.error else Color.Transparent,
                    unfocusedIndicatorColor = if (state.isEmailError) MaterialTheme.colors.error else Color.Transparent,
                    cursorColor = Color.Black
                ),
                textStyle = InputField,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                )
            )
            AnimatedVisibility(state.isEmailError) {
                Text(
                    text = state.emailError ?: "",
                    color = MaterialTheme.colors.error,
                    modifier = Modifier.padding(4.dp)
                )
            }
            Text(
                text = "Password",
                style = LabelStyle,
                modifier = Modifier.padding(top = 20.dp)
            )
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    onEvent(LoginEvent.PasswordChanged(it))
                },
                placeholder = { Text(text = "Password", style = InputHint) },
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = WhiteSmoke,
                    focusedIndicatorColor = if (state.isPasswordError) MaterialTheme.colors.error else Color.Transparent,
                    unfocusedIndicatorColor = if (state.isPasswordError) MaterialTheme.colors.error else Color.Transparent,
                    cursorColor = Color.Black
                ),
                textStyle = InputField,
                visualTransformation =
                if (showPassword) { VisualTransformation.None } else { PasswordVisualTransformation() },
                trailingIcon = {
                    if (showPassword) {
                        IconButton(onClick = {
                            showPassword = false
                        }) {
                            Icon(
                                imageVector = Icons.Default.VisibilityOff,
                                null,
                                tint = Gray
                            )
                        }
                    } else {
                        IconButton(onClick = {
                            showPassword = true
                        }) {
                            Icon(
                                imageVector = Icons.Default.Visibility,
                                null,
                                tint = Gray
                            )
                        }
                    }
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                )
            )
            AnimatedVisibility(state.isPasswordError) {
                Text(
                    text = state.passwordError ?: "",
                    color = MaterialTheme.colors.error,
                    modifier = Modifier.padding(4.dp)
                )
            }
            Spacer(Modifier.height(16.dp))
            Button(
                onClick = { onEvent(LoginEvent.LoginClicked(email, password)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                shape = Shapes.medium,
                colors = ButtonDefaults.buttonColors(backgroundColor = Primary)
            ) {
                Text(
                    text = "LOGIN",
                    modifier = Modifier.padding(top = 6.dp, bottom = 6.dp),
                    style = ButtonText
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Don't have an account?",
                    textAlign = TextAlign.Center,
                    style = SubHeader
                )
                Spacer(Modifier.width(4.dp))
                ClickableText(
                    text = AnnotatedString("Register Now".uppercase()),
                    onClick = {
                      // TODO - Add navigation to Register screen
                    },
                    style = ClickableText
                )
            }
            Column(modifier = Modifier.padding(bottom = paddingBottom)) { }
        }
    }
}
