package dima_merzlov.com.testtask5pro.data

import androidx.room.Entity
import androidx.room.OnConflictStrategy
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.PrimaryKey


data class User(

    var second_name: String,
    var name: String,
    var phone_number: String,
    var phone_code: String
)