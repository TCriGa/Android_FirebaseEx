package br.com.zup.authentication.data.response


import com.google.gson.annotations.SerializedName


data class NewsGoogleResponse(
    @SerializedName("articles")
    var articles: List<ArticleResponse> = listOf(),
    @SerializedName("status")
    var status: String = "",
    @SerializedName("totalResults")
    var totalResults: Int = 0
)