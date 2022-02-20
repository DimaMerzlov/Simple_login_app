package dima_merzlov.com.testtask5pro.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dima_merzlov.com.testtask5pro.data.User
import dima_merzlov.com.testtask5pro.model.Repository
import dima_merzlov.com.testtask5pro.model.room.UserEntity
import kotlinx.coroutines.*

class ActivityViewModel(private val repository: Repository) : ViewModel() {

    val userLoaded = MutableLiveData<Boolean>()
    var job: Job? = null
    var user: User? = null

    fun getUser(phone: String, password: String) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getUser(phone, password)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    userLoaded.postValue(true)
                    user = response.body()?.user
                    saveToDatabase(user)
                } else {
                    userLoaded.postValue(false)
                    Log.d("Error ", response.message())
                }
            }
        }

    }


    private suspend fun saveToDatabase(user: User?) {
        job = CoroutineScope(Dispatchers.IO).launch {
            if (user != null) {
                try {
                    repository.insert(
                        UserEntity(
                            0,
                            user.second_name,
                            user.name,
                            user.phone_number,
                            user.phone_code
                        )
                    )
                } catch (e: Exception) {
                    Log.d("Exception", e.message.toString())
                }
            }
        }

    }

    fun getUserInfo(): LiveData<UserEntity> {
        var userEntity = MutableLiveData<UserEntity>()
        job = CoroutineScope(Dispatchers.IO).launch {
            userEntity.postValue(repository.getAllUser()?.get(0))
        }
        return userEntity
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}