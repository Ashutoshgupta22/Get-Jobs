package com.aspark.lokalassign.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aspark.lokalassign.repository.JobsRepository

class ViewModelFactory(
    private val repository: JobsRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(JobsViewModel::class.java) -> {
                JobsViewModel(repository) as T
            }
            modelClass.isAssignableFrom(BookmarksViewModel::class.java) -> {
                BookmarksViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}