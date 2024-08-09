package com.aspark.lokalassign.viewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aspark.lokalassign.model.Job
import com.aspark.lokalassign.repository.JobsRepository
import com.aspark.lokalassign.ui.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class JobsViewModel(
    private val repository: JobsRepository
) : ViewModel() {

    private val _selectedJob = MutableStateFlow(Job())
    val selectedJob = _selectedJob.asStateFlow()

    private val _jobs = MutableStateFlow<UiState<List<Job>>>(UiState.Loading)
    val jobs: StateFlow<UiState<List<Job>>> = _jobs.asStateFlow()

    private val _bookmarkedJobs = MutableStateFlow<UiState<List<Job>>>(UiState.Loading)
    val bookmarkedJobs: StateFlow<UiState<List<Job>>> = _bookmarkedJobs.asStateFlow()

    private val _isBookmarked = MutableStateFlow(false)
    val isBookmarked: StateFlow<Boolean> = _isBookmarked

    private var currentPage = 1;
    var isLoading = false
    var endReached = false

    init {
        getJobs()
    }

     fun getBookmarkedJobs() {
        viewModelScope.launch {
            repository.getBookmarkedJobs().collect { bookmarkedJobs ->
                _bookmarkedJobs.value = UiState.Success(bookmarkedJobs)
                Log.i("JobsViewModel", "getBookmarkedJobs: ${_bookmarkedJobs.value}")
            }
        }
    }

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

    fun bookmarkJob(job: Job) {
        viewModelScope.launch {
            _isBookmarked.value = true
            repository.bookmarkJob(job)
        }
    }

    fun removeBookmark(jobId: Int) {
        viewModelScope.launch {
            _isBookmarked.value = false
            repository.removeBookmark(jobId)
        }
    }

    fun isBookmarked(id: Int) {
          viewModelScope.launch {
              _isBookmarked.value = repository.isJobBookmarked(id)
              Log.i("JobsViewModel", "isBookmarked: id: $id ${_isBookmarked.value}")
          }
    }
}