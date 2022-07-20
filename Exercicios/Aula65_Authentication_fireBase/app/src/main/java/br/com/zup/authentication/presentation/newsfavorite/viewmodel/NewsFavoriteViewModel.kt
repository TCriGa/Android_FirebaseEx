package br.com.zup.authentication.presentation.newsfavorite.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.zup.authentication.data.repository.NewsFavoriteRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class NewsFavoriteViewModel : ViewModel() {
    private val favoriteRepository = NewsFavoriteRepository()

    private var _favoriteListState = MutableLiveData<List<String>>()
    val favoriteListState: LiveData<List<String>> = _favoriteListState

    private var _messageState = MutableLiveData<String>()
    val messageState: LiveData<String> = _messageState


    fun getListFavorite() {
        favoriteRepository.getListNewsFavorite()
            .addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    Log.d(TAG, "onChildAdded:" + snapshot.key!!)

                    val favoriteTitle = mutableListOf<String>()

                    for (resultSnapshot in snapshot.children) {
                        val favoriteResponse = resultSnapshot.value
                        favoriteResponse?.let {
                            favoriteTitle.add(it as String)

                        }
                        _favoriteListState.value = favoriteTitle
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    _messageState.value = error.message
                }
            })
    }

    fun removeFavorite() {
        favoriteRepository.databaseReference()
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (removeSnapshot in snapshot.children) {
                     removeSnapshot.ref.removeValue()

                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    _messageState.value = error.message
                }
            })
    }


}

