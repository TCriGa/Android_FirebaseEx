package br.com.zup.authentication.data.datasource.model


import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("data")
    var `data`: List<Data> = listOf(),
    @SerializedName("pagination")
    var pagination: Pagination = Pagination()
)