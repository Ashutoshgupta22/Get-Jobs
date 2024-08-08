package com.aspark.lokalassign.repository

import android.util.Log
import com.aspark.lokalassign.model.Job
import com.aspark.lokalassign.model.JobResponse
import com.aspark.lokalassign.network.JobsApi
import com.aspark.lokalassign.ui.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class JobsRepository(private val jobsApi: JobsApi) {

    fun getJobs(): Flow<UiState<List<Job>>> = flow {

        emit(UiState.Loading)

        try {
            val jobs = jobsApi.getJobs().results
            Log.i("JobsRepo", "getJobs: $jobs")
            emit(UiState.Success(jobs))

        } catch (e: Exception) {
            emit(UiState.Error(e.message ?: "Unknown Error"))
        }
    }
}