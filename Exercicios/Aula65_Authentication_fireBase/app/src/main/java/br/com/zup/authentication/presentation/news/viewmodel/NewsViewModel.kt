package br.com.zup.authentication.presentation.news.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.zup.authentication.data.remote.RetrofitService
import br.com.zup.authentication.data.repository.AuthenticationRepository
import br.com.zup.authentication.data.repository.NewsFavoriteRepository
import br.com.zup.authentication.data.response.ArticleResponse
import br.com.zup.authentication.data.response.NewsGoogleResponse
import br.com.zup.authentication.utillity.MESSAGE_FAVORITE_SUCCESS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsViewModel : ViewModel() {
    private val authenticationRepository = AuthenticationRepository()

    private val newsFavoriteRepository = NewsFavoriteRepository()

    private val _newsResponse = MutableLiveData<List<ArticleResponse>>()
    val newsResponse: LiveData<List<ArticleResponse>> = _newsResponse

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

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
                _message.value = "Tivemos um problema, tente novamente!"
            } finally {
                _loading.value = false
            }
        }
    }

    fun getUserName() = authenticationRepository.getNameUser()

    fun getUserEmail() = authenticationRepository.getEmailUser()

    fun logout() = authenticationRepository.logoutOut()

    fun saveNewsFavorite(savedNews: ArticleResponse) {
            newsFavoriteRepository.databaseReference().push()
                .setValue(savedNews) { error, _ ->
                    if (error != null) {
                        _message.value = error.message
                    }
                    _message.value = MESSAGE_FAVORITE_SUCCESS

                }
        }
    }

