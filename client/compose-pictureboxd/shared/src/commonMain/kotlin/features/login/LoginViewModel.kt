package features.login

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.LoginClicked -> {
                checkFields(event.email, event.password)
            }
            is LoginEvent.PasswordChanged -> {
                _state.update {
                    it.copy(
                        isPasswordError = false,
                        passwordError = null
                    )
                }
            }
            is LoginEvent.EmailChanged -> {
                _state.update {
                    it.copy(
                        isEmailError = false,
                        emailError = null
                    )
                }
            }
        }
    }

    private fun checkFields(email: String, password: String) {
        if (email.isEmpty() && password.isEmpty()) {
            _state.update {
                it.copy(
                    isEmailError = true,
                    isPasswordError = true,
                    emailError = "Required",
                    passwordError = "Required"
                )
            }
        } else if (email.isEmpty()) {
            _state.update {
                it.copy(
                    isEmailError = true,
                    isPasswordError = false,
                    emailError = "Required"
                )
            }
        } else if (password.isEmpty()) {
            _state.update {
                it.copy(
                    isEmailError = false,
                    isPasswordError = true,
                    passwordError = "Required"
                )
            }
        } else {
            // TODO - perform login
        }
    }
}
