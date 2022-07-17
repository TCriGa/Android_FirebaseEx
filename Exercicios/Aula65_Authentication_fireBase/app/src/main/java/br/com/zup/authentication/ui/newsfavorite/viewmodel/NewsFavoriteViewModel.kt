package br.com.zup.authentication.ui.newsfavorite.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.zup.authentication.data.datasource.model.Article
import br.com.zup.authentication.domain.repository.NewsFavoriteRepository
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

                    val favoriteList = mutableListOf<String>()

                    for (resultSnapshot in snapshot.children) {
                        val favoriteResponse = resultSnapshot.getValue(String::class.java)
                        favoriteResponse?.let { favoriteList.add(it) }
                    }
                    _favoriteListState.value = favoriteList
                }

                override fun onCancelled(error: DatabaseError) {
                    _messageState.value = error.message
                }
            })
    }

    fun removeFavorite(remove : String) {
        val uri: Uri = Uri.parse(remove)
        val pathImage: String? = uri.lastPathSegment?.replace(".jpg", "")
        favoriteRepository.databaseReference()
            .child("$pathImage").removeValue()
    }
}