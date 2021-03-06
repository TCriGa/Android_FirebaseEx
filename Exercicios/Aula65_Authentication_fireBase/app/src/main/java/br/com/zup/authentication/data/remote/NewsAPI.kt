package br.com.zup.authentication.data.remote

import br.com.zup.authentication.data.response.NewsGoogleResponse
import br.com.zup.authentication.data.remote.RetrofitService.Companion.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("top-headlines")
    suspend fun getAllNews(
        @Query("apiKey")
        apiKey: String? = API_KEY,
        @Query("category")
        category : String? = "general",
        @Query("language")
        language: String? = "pt",
        @Query("country")
        country: String? = "br"
    ): NewsGoogleResponse
}