package br.com.zup.authentication.ui.news.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.zup.authentication.data.datasource.model.Article
import br.com.zup.authentication.data.datasource.model.NewsGoogleResponse
import br.com.zup.authentication.data.datasource.remote.RetrofitService
import br.com.zup.authentication.domain.repository.AuthenticationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsViewModel : ViewModel() {
private val authenticationRepository = AuthenticationRepository()
    private val _newsResponse = MutableLiveData<List<Article>>()
    val newsResponse = _newsResponse

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun getAllInformation() {
        _loading.value = true
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    RetrofitService.apiService.getAllNews()
                }
                _newsResponse.value = response.articles
            } catch (ex: Exception) {
                _errorMessage.value = "Tivemos um problema, tente novamente!"
            } finally {
                _loading.value = false
            }
        }
    }

    fun getUserName() = authenticationRepository.getNameUser()

    fun getUserEmail() = authenticationRepository.getEmailUser()

    fun logout() = authenticationRepository.logoutOut()

}