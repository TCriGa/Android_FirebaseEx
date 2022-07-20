package br.com.zup.authentication.data.response


import com.google.gson.annotations.SerializedName


data class ArticleResponse(
    @SerializedName("author")
    var author: String? = null,
    @SerializedName("content")
    var content: String = "",
    @SerializedName("description")
    var description: String = "",
    @SerializedName("publishedAt")
    var publishedAt: String = "",
    @SerializedName("sourceResponse")
    var sourceResponse: SourceResponse = SourceResponse(),
    @SerializedName("title")
    var title: String = "",
    @SerializedName("url")
    var url: String = "",
    @SerializedName("urlToImage")
    var urlToImage: String? = null

)