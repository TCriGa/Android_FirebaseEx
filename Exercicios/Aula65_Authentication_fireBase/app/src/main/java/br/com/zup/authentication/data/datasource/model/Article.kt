package br.com.zup.authentication.data.datasource.model


import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("author")
    var author: String? = null,
    @SerializedName("content")
    var content: String = "",
    @SerializedName("description")
    var description: String = "",
    @SerializedName("publishedAt")
    var publishedAt: String = "",
    @SerializedName("source")
    var source: Source = Source(),
    @SerializedName("title")
    var title: String = "",
    @SerializedName("url")
    var url: String = "",
    @SerializedName("urlToImage")
    var urlToImage: String? = null
)