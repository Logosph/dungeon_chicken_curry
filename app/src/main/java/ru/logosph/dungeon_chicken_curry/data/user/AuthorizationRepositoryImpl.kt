package ru.logosph.dungeon_chicken_curry.data.user

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.FormBody
import okhttp3.Headers
import okhttp3.OkHttpClient
import okhttp3.Request
import ru.logosph.dungeon_chicken_curry.domain.authentication.AuthorizationRepository
import ru.logosph.dungeon_chicken_curry.domain.authentication.UserModel

class AuthorizationRepositoryImpl : AuthorizationRepository {

    private val url = "https://chgk-server.onrender.com/api"

    override suspend fun login(user: UserModel, context: Context): Boolean = withContext(Dispatchers.IO) {
        val client = OkHttpClient()

        val formBuilder = FormBody.Builder()
            .add("username", user.username)
            .add("password", user.password)
            .build()

        val request = Request.Builder()
            .url("$url/users/login")
            .post(formBuilder)
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) return@withContext false
            val token = response.body?.string()
            val sharedPref = context.getSharedPreferences("token", Context.MODE_PRIVATE)
            with(sharedPref.edit()) {
                putString("token", token)
                apply()
            }
            return@withContext true
        }
    }

    override suspend fun loginByToken(context: Context): Boolean  = withContext(Dispatchers.IO) {
        val sharedPref = context.getSharedPreferences("token", Context.MODE_PRIVATE)
        val token = sharedPref.getString("token", null)
        if (token == null) return@withContext false

        val client = OkHttpClient()

        val headers = Headers
            .Builder()
            .add("Authorization", "Bearer $token")

        val request = Request.Builder()
            .url("$url/login")
            .headers(headers.build())
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) return@withContext false
            val newToken = response.body?.string()
            with(sharedPref.edit()) {
                putString("token", newToken)
                apply()
            }
            return@withContext true
        }
    }

    override fun logout(context: Context): Boolean {
        val sharedPref = context.getSharedPreferences("token", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            remove("token")
            apply()
        }
        return true
    }

    override suspend fun register(user: UserModel, context: Context): Boolean  = withContext(Dispatchers.IO) {
        val client = OkHttpClient()

        val formBuilder = FormBody.Builder()
            .add("username", user.username)
            .add("password", user.password)
            .build()

        val request = Request.Builder()
            .url("$url/users/register")
            .post(formBuilder)
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) return@withContext false
            return@withContext login(user, context)
        }
    }
}