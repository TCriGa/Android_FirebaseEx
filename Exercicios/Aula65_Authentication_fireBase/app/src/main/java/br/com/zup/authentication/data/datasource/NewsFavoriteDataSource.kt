package br.com.zup.authentication.data.datasource

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.Query
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class NewsFavoriteDataSource {

    private val authentication: FirebaseAuth = Firebase.auth
    private var database: DatabaseReference = Firebase.database.reference
    private val reference =
        database.database.getReference("News_Favorite ${authentication.currentUser?.uid}")

    fun databaseReference() = reference

    fun getListNewsFavorite(): Query {
        return reference.orderByValue()
    }

}