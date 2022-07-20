package br.com.zup.authentication.data.repository

import br.com.zup.authentication.data.datasource.NewsFavoriteDataSource
import com.google.firebase.database.Query

class NewsFavoriteRepository {
    private val newsFavoriteDataSource: NewsFavoriteDataSource by lazy {
        NewsFavoriteDataSource()
    }

    fun databaseReference() = newsFavoriteDataSource.databaseReference()

    fun getListNewsFavorite(): Query {
        return newsFavoriteDataSource.getListNewsFavorite()
    }

}