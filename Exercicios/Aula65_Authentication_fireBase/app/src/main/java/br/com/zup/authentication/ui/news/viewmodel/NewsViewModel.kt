package br.com.zup.authentication.ui.news.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.zup.authentication.data.datasource.model.Article
import br.com.zup.authentication.data.datasource.model.NewsGoogleResponse
import br.com.zup.authentication.data.datasource.remote.RetrofitService
import br.com.zup.authentication.domain.repository.AuthenticationRepository
import br.com.zup.authentication.domain.repository.NewsFavoriteRepository
import br.com.zup.authentication.utillity.MESSAGE_FAVORITE_SUCCESS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsViewModel : ViewModel() {
    private val authenticationRepository = AuthenticationRepository()

    private val newsFavoriteRepository = NewsFavoriteRepository()

    private val _newsResponse = MutableLiveData<List<Article>>()
    val newsResponse: LiveData<List<Article>> = _newsResponse

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

    fun saveNewsFavorite() {
        val title = _newsResponse.value?.onEachIndexed { index, article ->
            article.title
        }
        val imagePath = getImagePath()
        newsFavoriteRepository.databaseReference().child("$imagePath")
            .setValue(title) { error, _ ->
                if (error != null) {
                    _message.value = error.message
                }
                _message.value = MESSAGE_FAVORITE_SUCCESS
            }
    }

    private fun getImagePath() {
      _newsResponse.value?.onEachIndexed { index, article ->
            article.title
          article.urlToImage
          article.description
        }
    }
}