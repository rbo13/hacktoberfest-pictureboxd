package features.login

sealed interface LoginEvent {
    data class EmailChanged(val value: String) : LoginEvent
    data class PasswordChanged(val value: String) : LoginEvent
    data class LoginClicked(val email: String, val password: String) : LoginEvent
}
