package br.com.zup.authentication.data.datasource.model


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("author")
    var author: String? = null,
    @SerializedName("category")
    var category: String = "",
    @SerializedName("country")
    var country: String = "",
    @SerializedName("description")
    var description: String = "",
    @SerializedName("image")
    var image: String? = null,
    @SerializedName("language")
    var language: String = "",
    @SerializedName("published_at")
    var publishedAt: String = "",
    @SerializedName("source")
    var source: String = "",
    @SerializedName("title")
    var title: String = "",
    @SerializedName("url")
    var url: String = ""
)