package com.example.uodi


import android.os.AsyncTask
import okhttp3.OkHttpClient
import okhttp3.Request


class getSection : AsyncTask<Void, Void, String?>() {
    override fun doInBackground(vararg params: Void?): String? {
        val client = OkHttpClient()
        val url = "http://210.115.227.109/phpmyadmin/UU/getSection.php"
        val request = Request.Builder()
            .url(url)
            .build()

        return try {
            val response = client.newCall(request).execute()
            response.body?.string()?.replace("\n","")?.replace("\r","").toString()

        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}

class getTime : AsyncTask<Void, Void, String?>() {
    override fun doInBackground(vararg params: Void?): String? {
        val client = OkHttpClient()
        val url = "http://210.115.227.109/phpmyadmin/UU/getTime.php"
        val request = Request.Builder()
            .url(url)
            .build()

        return try {
            val response = client.newCall(request).execute()
            response.body?.string()?.replace("\n","")?.replace("\r","").toString()

        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}


