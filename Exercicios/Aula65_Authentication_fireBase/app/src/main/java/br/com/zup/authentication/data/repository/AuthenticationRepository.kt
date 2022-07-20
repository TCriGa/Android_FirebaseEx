package br.com.zup.authentication.data.repository

import br.com.zup.authentication.data.datasource.AuthenticationDataSource
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

class AuthenticationRepository {

    private val authenticationDataSource: AuthenticationDataSource by lazy {
        AuthenticationDataSource()
    }


    fun registerUser(email: String, password: String): Task<AuthResult> {
        return authenticationDataSource.registerUser(email, password)
    }

    fun updateUserProfile(name: String): Task<Void>? {
        return authenticationDataSource.updateUserProfile(name)
    }

    fun logoutOut() {
        authenticationDataSource.logoutOut()
    }

    fun loginUser(email: String, password: String): Task<AuthResult> {
        return authenticationDataSource.loginUser(email, password)
    }

    fun getNameUser(): String = authenticationDataSource.getNameUser()

    fun getEmailUser(): String = authenticationDataSource.getEmailUser()
}