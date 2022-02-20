package dima_merzlov.com.testtask5pro.model

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import dima_merzlov.com.testtask5pro.data.Info
import dima_merzlov.com.testtask5pro.data.User
import dima_merzlov.com.testtask5pro.model.retrofit.RetrofitService
import dima_merzlov.com.testtask5pro.model.room.UserDatabase
import dima_merzlov.com.testtask5pro.model.room.UserEntity
import okhttp3.*
import okhttp3.internal.Util.EMPTY_REQUEST
import retrofit2.Response
import java.io.IOException

class Repository(var application: Application, var retrofitService: RetrofitService) {

    suspend fun getUser(phoneNumber: String, password: String): Response<Info> {
        var phoneCode = ""
        var number = ""
        if (phoneNumber.length > 6) {
            phoneCode = phoneNumber.substring(0, 4)
            number = phoneNumber.substring(4)
        }
        return retrofitService.getUser(phoneCode, number, password)
    }

    private val db = UserDatabase.getInstance(application)
    suspend fun insert(user: UserEntity) {
        db?.userDao()?.insert(user)
    }

    suspend fun getAllUser(): List<UserEntity>? {
        return db?.userDao()?.getAll()
    }
}