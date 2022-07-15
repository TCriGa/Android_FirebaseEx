package br.com.zup.authentication.ui.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.zup.authentication.domain.model.User
import br.com.zup.authentication.domain.repository.AuthenticationRepository
import br.com.zup.authentication.utillity.*

class LoginViewModel : ViewModel() {
    private val authenticationRepository = AuthenticationRepository()
    private var _loginState = MutableLiveData<User>()
    val loginState= _loginState

    private var _errorState = MutableLiveData<String>()
    val errorState = _errorState

    fun validateDataUser(user: User){
        when {

            user.email.isEmpty() ->{
                _errorState.value = ERROR_EMAIL_MESSAGE
            }
            user.password.isEmpty() ->{
                _errorState.value = ERROR_PASSWORD_MESSAGE
            }
            else ->{
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