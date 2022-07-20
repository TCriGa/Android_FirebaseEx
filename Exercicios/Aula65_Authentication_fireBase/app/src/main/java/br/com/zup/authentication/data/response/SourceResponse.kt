package br.com.zup.authentication.data.response


import com.google.firebase.database.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName

@IgnoreExtraProperties
data class SourceResponse(
    @SerializedName("id")
    var id: String = "",
    @SerializedName("name")
    var name: String = ""
){

}

