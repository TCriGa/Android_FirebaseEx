package br.com.zup.authentication.data.datasource.model


import com.google.gson.annotations.SerializedName

data class Pagination(
    @SerializedName("count")
    var count: Int = 0,
    @SerializedName("limit")
    var limit: Int = 0,
    @SerializedName("offset")
    var offset: Int = 0,
    @SerializedName("total")
    var total: Int = 0
)