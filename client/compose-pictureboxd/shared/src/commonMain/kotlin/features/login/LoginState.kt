package features.login

data class LoginState(
    val isEmailError: Boolean = false,
    val isPasswordError: Boolean = false,
    val emailError: String? = null,
    val passwordError: String? = null,
    val error: String? = null
)
