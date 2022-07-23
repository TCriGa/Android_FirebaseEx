package br.com.zup.authentication.presentation.login.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.com.zup.authentication.data.repository.AuthenticationRepository
import br.com.zup.authentication.domain.model.User
import br.com.zup.authentication.utillity.ERROR_EMAIL_MESSAGE
import br.com.zup.authentication.utillity.ERROR_LOGIN_MESSAGE
import br.com.zup.authentication.utillity.ERROR_PASSWORD_MESSAGE

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val authenticationRepository = AuthenticationRepository()
    private var _loginState = MutableLiveData<User>()
    val loginState = _loginState

    private var _errorState = MutableLiveData<String>()
    val errorState = _errorState


    fun validateDataUser(user: User) {
        when {

            user.email.isEmpty() -> {
                _errorState.value = ERROR_EMAIL_MESSAGE
            }
            user.password.isEmpty() -> {
                _errorState.value = ERROR_PASSWORD_MESSAGE
            }
            else -> {
                loginUser(user)
            }
        }
    }

    private fun loginUser(user: User) {
        try {
            authenticationRepository.loginUser(
                user.email,
                user.password
            ).addOnSuccessListener {
                _loginState.value = user

            }.addOnFailureListener {
                _errorState.value = ERROR_LOGIN_MESSAGE + it.message
            }
        } catch (ex: Exception) {
            _errorState.value = ex.message
        }
    }
}

