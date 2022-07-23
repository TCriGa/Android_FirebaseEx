package br.com.zup.authentication.presentation.newsfavorite.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.zup.authentication.data.repository.NewsFavoriteRepository
import br.com.zup.authentication.data.response.ArticleResponse
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class NewsFavoriteViewModel : ViewModel() {
    private val favoriteRepository = NewsFavoriteRepository()
    private var _favoriteListState = MutableLiveData<HashMap<String, ArticleResponse>>()
    val favoriteListState: LiveData<HashMap<String, ArticleResponse>> = _favoriteListState

    private var _messageState = MutableLiveData<String>()
    val messageState: LiveData<String> = _messageState

    fun getListFavorite() {
        favoriteRepository.getListNewsFavorite().addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d(TAG, "onChildAdded:" + snapshot.key!!)

                val favoriteNews = hashMapOf<String, ArticleResponse>()


                for (resultSnapshot in snapshot.children) {
                    val favoriteResponse = resultSnapshot.getValue(ArticleResponse::class.java)
                    val favoriteKey = resultSnapshot.key.toString()
                    favoriteListState.let {
                        favoriteNews[favoriteKey] = favoriteResponse as ArticleResponse
                    }
                }
                _favoriteListState.value = favoriteNews
            }

            override fun onCancelled(error: DatabaseError) {
                _messageState.value = error.message
            }
        })
    }

    fun removeFavorite(removeNews: String?) {
        favoriteRepository.databaseReference().child("$removeNews").removeValue()
    }
}





