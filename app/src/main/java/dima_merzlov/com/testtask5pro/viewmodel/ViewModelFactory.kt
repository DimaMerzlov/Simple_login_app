package dima_merzlov.com.testtask5pro.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dima_merzlov.com.testtask5pro.model.Repository

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val repository: Repository)
    : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ActivityViewModel(repository) as T
    }
}