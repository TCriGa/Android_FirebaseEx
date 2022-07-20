package br.com.zup.authentication.presentation.notification.viewmodel

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import br.com.zup.authentication.domain.model.NotificationN
import br.com.zup.authentication.utillity.*

class NotificationViewModel(application: Application) : AndroidViewModel(application) {

    private var context = application

    private var _notificationState = MutableLiveData<NotificationN>()
    val notificationState: LiveData<NotificationN> = _notificationState

    private var _currentToken = MutableLiveData<String>()
    var currentToken: LiveData<String> = _currentToken

    private var tokenReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            try {
                _currentToken.value = intent?.getStringExtra(TOKEN_KEY).toString()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    private val messageReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            try {
                val title = intent?.getStringExtra(KEY_TITLE_NOTIFICATION)
                val bodyLast = intent?.getStringExtra(KEY_BODY_NOTIFICATION)
                _notificationState.value = NotificationN(
                    bodyLast,
                    title
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    init {
        LocalBroadcastManager.getInstance(context).registerReceiver(
            (tokenReceiver),
            IntentFilter(CURRENT_KEY)
        )
        LocalBroadcastManager.getInstance(context)
            .registerReceiver((messageReceiver), IntentFilter(KEY_INTENT))

    }
}