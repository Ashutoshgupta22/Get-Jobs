package com.aspark.lokalassign.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aspark.lokalassign.model.Job
import kotlinx.coroutines.flow.Flow

@Dao
interface JobDao {

    @Query("SELECT * FROM bookmarked_jobs")
    fun getAllBookmarkedJobs(): Flow<List<JobEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJob(job: JobEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM bookmarked_jobs WHERE job_id = :jobId)")
    suspend fun isJobBookmarked(jobId: Int): Boolean

    @Query("DELETE FROM bookmarked_jobs WHERE job_id = :jobId")
    suspend fun deleteJob(jobId: Int)
}