package br.com.zup.authentication.data.datasource.remote

import br.com.zup.authentication.data.datasource.remote.RetrofitService.Companion.API_KEY
import br.com.zup.authentication.data.datasource.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("news")
    suspend fun getAllNews(
        @Query("access_key")
        access_key: String? = API_KEY
    ) : NewsResponse
}