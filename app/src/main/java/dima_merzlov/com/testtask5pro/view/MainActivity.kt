package dima_merzlov.com.testtask5pro.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import dima_merzlov.com.testtask5pro.databinding.ActivityMainBinding
import dima_merzlov.com.testtask5pro.model.Repository
import dima_merzlov.com.testtask5pro.model.retrofit.RetrofitService
import dima_merzlov.com.testtask5pro.viewmodel.ActivityViewModel
import dima_merzlov.com.testtask5pro.viewmodel.ViewModelFactory
import okhttp3.*


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) //initializing the binding class
        setContentView(binding.root) // we now set the contentview as the binding.root

        supportActionBar?.title="Sign in"
        val retrofitService = RetrofitService.getInstance()

        val factory = ViewModelFactory(Repository(application, retrofitService))
        val viewModel = ViewModelProvider(this, factory)[ActivityViewModel::class.java]
        binding.button.setOnClickListener {
            viewModel.getUser(
                binding.edtPhone.text.toString(),
                binding.edtPassword.text.toString()
            )
        }

        viewModel.userLoaded.observe(this) {
            if (it) startActivity(Intent(this, InfoActivity::class.java))
        }
    }
}