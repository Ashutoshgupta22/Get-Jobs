package com.aspark.lokalassign.network

import com.aspark.lokalassign.model.Job
import com.aspark.lokalassign.model.JobResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface JobsApi {

    @GET("common/jobs/")
    suspend fun getJobs(@Query("page") page: Int = 1): JobResponse
}