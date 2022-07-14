package br.com.zup.authentication.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class User(
    var name: String = "",
    var email: String = "",
    var password: String = ""
) : Parcelable {

}