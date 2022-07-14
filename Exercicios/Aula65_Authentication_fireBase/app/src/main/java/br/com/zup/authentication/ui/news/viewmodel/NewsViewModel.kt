package br.com.zup.authentication.ui.news.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.zup.authentication.data.datasource.remote.RetrofitService
import br.com.zup.authentication.data.datasource.model.NewsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsViewModel : ViewModel() {

    private val _newsResponse = MutableLiveData<NewsResponse>()
    val newsResponse = MutableLiveData<NewsResponse>()
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun getImageCoffee() {
        _loading.value = true
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    RetrofitService.apiService.getAllNews()
                }
                _newsResponse.value = response
            } catch (ex: Exception) {
                _errorMessage.value = "Tivemos um problema, tente novamente!"
            } finally {
                _loading.value = false
            }
        }
    }
}