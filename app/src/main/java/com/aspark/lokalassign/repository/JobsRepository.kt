package com.aspark.lokalassign.repository

import android.util.Log
import com.aspark.lokalassign.model.Job
import com.aspark.lokalassign.model.toEntity
import com.aspark.lokalassign.model.toModel
import com.aspark.lokalassign.network.JobsApi
import com.aspark.lokalassign.room.JobDao
import com.aspark.lokalassign.ui.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlin.math.log

class JobsRepository(
    private val jobsApi: JobsApi,
    private val jobDao: JobDao
) {

    fun getJobs(page: Int): Flow<UiState<List<Job>>> = flow {

        emit(UiState.Loading)

        try {
            val jobs = jobsApi.getJobs(page).results
//            Log.i("JobsRepo", "getJobs: $jobs")
            emit(UiState.Success(jobs))

        } catch (e: Exception) {
            emit(UiState.Error(e.message ?: "Unknown Error"))
        }
    }

    fun getBookmarkedJobs(): Flow<List<Job>> {

        return jobDao.getAllBookmarkedJobs().map { jobEntityList ->
            jobEntityList.map { jobEntity ->
                jobEntity.toModel()
            }
        }
    }

    suspend fun bookmarkJob(job: Job) {
        jobDao.insertJob(job.toEntity())
        Log.i("JobsRepository", "bookmarkJob: id: ${job.toEntity()}")
    }

    suspend fun removeBookmark(jobId: Int) {
        jobDao.deleteJob(jobId)
    }

    suspend fun isJobBookmarked(id: Int): Boolean {
        return jobDao.isJobBookmarked(id)
    }
}