package br.com.zup.authentication.domain.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.ktx.Firebase

class NewsFavoriteRepository {

    private val authentication: FirebaseAuth = Firebase.auth
    private val database = FirebaseDatabase.getInstance()
    private val reference =
        database.getReference("News_Favorite ${authentication.currentUser?.uid}")

    fun databaseReference() = reference

    fun getListNewsFavorite() : Query{
        return reference.orderByValue()
    }
}