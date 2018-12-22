package com.example.jonathansimonney.igeneration

import android.util.Log
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFCMClass : FirebaseMessagingService() {

    private val TAG = "JSA-FCM"
    private lateinit var database : DatabaseReference

    override fun onNewToken(token: String?) {
        Log.d("clementRealToken", "Refreshed token: $token")

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        database = FirebaseDatabase.getInstance().reference
//        sendRegistrationToServer(token)
        database.child("fcmToken").setValue(token).addOnSuccessListener {
            Log.d("clementRealToken", "updated token in db: $token")
        }
                .addOnFailureListener {
                    Log.e("clementRealToken", "refused refresh: $it")
                }
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        Log.d(TAG, "From: " + remoteMessage!!.from)
    }

}