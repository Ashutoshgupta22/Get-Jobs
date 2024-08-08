package com.aspark.lokalassign.viewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.aspark.lokalassign.model.Job
import com.aspark.lokalassign.network.ApiClient
import com.aspark.lokalassign.repository.JobsRepository
import com.aspark.lokalassign.ui.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class JobsViewModel: ViewModel() {

    private val repository: JobsRepository = JobsRepository(ApiClient.jobsApi)

    val jobs: Flow<PagingData<Job>> = repository.getJobs().cachedIn(viewModelScope)
}