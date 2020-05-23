package org.themoviedb.util

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import java.io.File
import kotlin.coroutines.coroutineContext

fun Activity.isNetworkAvailable(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}