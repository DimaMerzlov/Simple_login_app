package dima_merzlov.com.testtask5pro.model.retrofit

import dima_merzlov.com.testtask5pro.data.Info
import dima_merzlov.com.testtask5pro.data.User
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import retrofit2.http.Query

interface RetrofitService {
    @POST("/login")
    suspend fun getUser(
        @Query("phone_code") phoneCode: String,
        @Query("phone_number") phoneNumber: String,
        @Query("password") password: String
    ): Response<Info>

    companion object {
        var retrofitService: RetrofitService? = null
        fun getInstance() : RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://tips-api-staging.crio-server.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }

    }
}