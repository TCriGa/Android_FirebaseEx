package br.com.zup.authentication.data.datasource.model


import com.google.gson.annotations.SerializedName


data class NewsGoogleResponse(
    @SerializedName("articles")
    var articles: List<Article> = listOf(),
    @SerializedName("status")
    var status: String = "",
    @SerializedName("totalResults")
    var totalResults: Int = 0
)