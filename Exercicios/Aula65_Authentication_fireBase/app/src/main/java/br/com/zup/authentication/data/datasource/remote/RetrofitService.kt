package br.com.zup.authentication.data.datasource.remote

import br.com.zup.authentication.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext


class RetrofitService {
    companion object {
        const val BASE_URL = "http://newsapi.org/v2/"
        const val API_KEY = "79e1923f01304fc19366030d096057c6"

        private val retrofit: Retrofit by lazy {

            val httpClient = OkHttpClient.Builder()
            SSLContext.getInstance("SSL")

            httpClient.readTimeout(30, TimeUnit.SECONDS)
            httpClient.connectTimeout(30, TimeUnit.SECONDS)
            httpClient.writeTimeout(30, TimeUnit.SECONDS)

            if (BuildConfig.DEBUG) {
                val logInterceptor = HttpLoggingInterceptor()
                logInterceptor.level = HttpLoggingInterceptor.Level.BODY
                httpClient.addInterceptor(logInterceptor)
            }

            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
        }

        @JvmStatic
        val apiService: NewsAPI
            get() = retrofit.create(NewsAPI::class.java)
    }
}