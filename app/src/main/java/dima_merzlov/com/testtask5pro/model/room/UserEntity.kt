package dima_merzlov.com.testtask5pro.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey(autoGenerate = true)  var id:Int,
    var second_name: String,
    var name: String,
    var phone_number: String,
    var phone_code: String
)