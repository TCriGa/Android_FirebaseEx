package br.com.zup.authentication.utillity

import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        Log.d(TAG, "Refreshed token: $token")
        val broadcaster = LocalBroadcastManager.getInstance(baseContext)
        val intent = Intent(CURRENT_KEY)
        intent.putExtra(TOKEN_KEY, token)
        broadcaster.sendBroadcast(intent)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        remoteMessage.notification?.let {
            val broadcaster = LocalBroadcastManager.getInstance(baseContext)
            val intent = Intent(KEY_INTENT)
            intent.putExtra(KEY_TITLE_NOTIFICATION,it.title)
            intent.putExtra(KEY_BODY_NOTIFICATION,  it.body)
            broadcaster.sendBroadcast(intent);
        }
    }
}
