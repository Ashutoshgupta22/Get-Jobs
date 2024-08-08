package com.aspark.lokalassign.viewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aspark.lokalassign.model.Job
import com.aspark.lokalassign.network.ApiClient
import com.aspark.lokalassign.repository.JobsRepository
import com.aspark.lokalassign.ui.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class JobsViewModel: ViewModel() {

    private val repository: JobsRepository = JobsRepository( ApiClient.jobsApi)
    private val _selectedJob = MutableStateFlow(Job())
    val selectedJob = _selectedJob.asStateFlow()
    private var currentPage = 1;
    var isLoading = false
    var endReached = false

    init {
        getJobs()
    }

    private val _jobs = MutableStateFlow<UiState<List<Job>>>(UiState.Loading)
    val jobs: StateFlow<UiState<List<Job>>> = _jobs.asStateFlow()

    private var isDataLoaded = false

    fun selectJob(job: Job) {
        _selectedJob.value = job
    }

    fun getJobs() {

        if (isLoading || endReached) return

        isLoading = true

        viewModelScope.launch {
            repository.getJobs(currentPage).collect { uiState ->

                when (uiState) {
                    is UiState.Success -> {
                        Log.i("JobsViewModel", "getJobs: Success page-$currentPage")

                        val currentList = if (_jobs.value is UiState.Success) {
                            (_jobs.value as UiState.Success<List<Job>>).data.plus(uiState.data)
                        }
                        else uiState.data.toMutableList()

                        if (uiState.data.isEmpty()) {
                            endReached = true
                        } else {
                            currentList.plus(uiState.data)
                            currentPage++
                        }
                            _jobs.value = UiState.Success(currentList)
                            isLoading = false
                    }

                    is UiState.Error -> {
                        Log.e("JobsViewModel",
                            "getJobs: Failed - ${uiState.message}")
                        isLoading = false
                        _jobs.value = uiState
                    }

                    is UiState.Loading -> {
                        Log.d("JobsViewModel", "getJobs: Loading")
                    }
                }
            }
        }
    }
}