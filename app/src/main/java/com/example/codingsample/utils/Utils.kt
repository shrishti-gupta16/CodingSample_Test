package com.example.codingsample.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.core.text.isDigitsOnly
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class Utils {

    companion object {

        const val API_URL = "fae7190d7e6433ec3a45285ffcf55c86"

        fun formatDate(time: String): String {
            val originalFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH)
            val targetFormat: DateFormat = SimpleDateFormat("MMM dd, YYYY")
            val date: Date = originalFormat.parse(time)
            return targetFormat.format(date)
        }

        fun formatDateTime(time: String): String {
            val originalFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH)
            val targetFormat: DateFormat = SimpleDateFormat("hh:mm aa")
            val date: Date = originalFormat.parse(time)
            return targetFormat.format(date)
        }

        /**
         * Connection Available or not
         */
        @SuppressLint("MissingPermission")
        fun isConnectionAvailable(context: Context): Boolean {
            val hasNet: Boolean
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            hasNet = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val network = connectivityManager.activeNetwork
                val capabilities = connectivityManager.getNetworkCapabilities(network)
                capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || capabilities.hasTransport(
                    NetworkCapabilities.TRANSPORT_CELLULAR
                ) || capabilities.hasTransport(
                    NetworkCapabilities.TRANSPORT_VPN
                ) || capabilities.hasTransport(
                    NetworkCapabilities.TRANSPORT_BLUETOOTH
                ))
            } else {
                connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo.isConnected
            }
            return hasNet
        }


        fun formatDoubleValue(text: String): Double {
            if (text.contains(".")) {
                val prefix = text.substringBefore(".")
                val postFix = text.substringAfter(".")
                val replacedPostFix = postFix.replace(".", "")
                return if (prefix.isDigitsOnly() && replacedPostFix.isDigitsOnly()) {
                    ("$prefix.$replacedPostFix").toDouble()
                } else {
                    0.0
                }
            } else {
                return if (text.isDigitsOnly()) {
                    text.toDouble()
                } else {
                    0.0
                }
            }
        }
    }
}
