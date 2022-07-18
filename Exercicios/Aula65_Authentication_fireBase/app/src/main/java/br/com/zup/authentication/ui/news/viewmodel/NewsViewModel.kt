package br.com.zup.authentication.ui.news.viewmodel

import android.net.Uri
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
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.internal.toImmutableList

class NewsViewModel : ViewModel() {
    private val authenticationRepository = AuthenticationRepository()

    private val newsFavoriteRepository = NewsFavoriteRepository()

    private val _newsResponse = MutableLiveData<NewsGoogleResponse>()
    val newsResponse: LiveData<NewsGoogleResponse> = _newsResponse

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
                _newsResponse.value = response
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

    fun saveNewsFavorite(article: Article) {
        newsFavoriteRepository.databaseReference().push()
            .setValue(article.title) { error, _ ->
                if (error != null) {
                    _message.value = error.message
                }
                _message.value = MESSAGE_FAVORITE_SUCCESS
            }
    }
}