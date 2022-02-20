package dima_merzlov.com.testtask5pro.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import dima_merzlov.com.testtask5pro.R
import dima_merzlov.com.testtask5pro.databinding.ActivityInfoBinding
import dima_merzlov.com.testtask5pro.databinding.ActivityMainBinding
import dima_merzlov.com.testtask5pro.model.Repository
import dima_merzlov.com.testtask5pro.model.retrofit.RetrofitService
import dima_merzlov.com.testtask5pro.viewmodel.ActivityViewModel
import dima_merzlov.com.testtask5pro.viewmodel.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class InfoActivity : AppCompatActivity() {

    lateinit var binding: ActivityInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val factory = ViewModelFactory(Repository(application, RetrofitService.getInstance()))
        val viewModel = ViewModelProvider(this, factory).get(ActivityViewModel::class.java)
        supportActionBar?.title="Profile"

        viewModel.getUserInfo().observe(this) {
            binding.edtName.setText(it.name)
            binding.edtSurname.setText(it.second_name)
            binding.edtPhoneNumber.setText("${it.phone_code}${it.phone_number}")
        }


    }
}