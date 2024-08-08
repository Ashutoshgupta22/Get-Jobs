package com.aspark.lokalassign.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aspark.lokalassign.model.Job
import com.aspark.lokalassign.network.ApiClient
import com.aspark.lokalassign.repository.JobsRepository
import com.aspark.lokalassign.ui.UiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class JobsViewModel: ViewModel() {

    private val repository: JobsRepository = JobsRepository( ApiClient.jobsApi)

    init {
        getJobs()
    }

    val jobs: StateFlow<UiState<List<Job>>> = repository.getJobs()
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            UiState.Loading
        )

    private var isDataLoaded = false;

   private fun getJobs() {

        if (isDataLoaded) return

        viewModelScope.launch {
            repository.getJobs().collect { uiState ->

                when (uiState) {
                    is UiState.Success -> {
                        Log.i("JobsViewModel", "getJobs: Success")
                        isDataLoaded = true
                    }

                    is UiState.Error -> {
                        Log.e("JobsViewModel",
                            "getJobs: Failed - ${uiState.message}")
                    }

                    is UiState.Loading -> {
                        Log.d("JobsViewModel", "getJobs: Loading")
                    }
                }
            }
        }
    }
}