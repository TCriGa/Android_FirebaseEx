package br.com.zup.authentication.ui.register.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.zup.authentication.domain.model.User
import br.com.zup.authentication.domain.repository.AuthenticationRepository
import br.com.zup.authentication.utillity.ERROR_CREATE_USER
import br.com.zup.authentication.utillity.ERROR_EMAIL_MESSAGE
import br.com.zup.authentication.utillity.ERROR_NAME_MESSAGE
import br.com.zup.authentication.utillity.ERROR_PASSWORD_MESSAGE

class RegisterViewModel : ViewModel() {

    private val authenticationRepository = AuthenticationRepository()

    private var _registerState = MutableLiveData<User>()
    val registerState: LiveData<User> = _registerState

    private var _errorState = MutableLiveData<String>()
    val errorState: LiveData<String> = _errorState

    fun validateDataUser(user: User){
        when {
            user.name.isEmpty() ->{
                _errorState.value = ERROR_NAME_MESSAGE
            }
            user.email.isEmpty() ->{
                _errorState.value = ERROR_EMAIL_MESSAGE
            }
            user.password.isEmpty() ->{
                _errorState.value = ERROR_PASSWORD_MESSAGE
            }
            else ->{
                registerUser(user)
            }
        }
    }
    private fun registerUser(user: User) {
        try {
            authenticationRepository.registerUser(
                user.email,
                user.password
            ).addOnSuccessListener {
                authenticationRepository.updateUserProfile(user.name)?.addOnSuccessListener {
                    _registerState.value = user
                }

            }.addOnFailureListener {
                _errorState.value = ERROR_CREATE_USER + it.message
            }
        } catch (ex: Exception) {
            _errorState.value = ex.message
        }
    }
}