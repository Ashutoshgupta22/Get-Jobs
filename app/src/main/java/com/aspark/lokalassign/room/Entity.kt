package com.aspark.lokalassign.room

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity("bookmarked_jobs")
@TypeConverters(JobTagConverter::class)
data class JobEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "job_id") val jobId: Int,
    val title: String,
    @ColumnInfo(name = "company_name") val companyName: String,
    @Embedded val primaryDetails: PrimaryDetailsEntity,
    val content: String,

    @TypeConverters(JobTagConverter::class)
    @ColumnInfo(name = "job_tags") val jobTags: List<JobTagEntity>,

    @ColumnInfo(name = "whatsapp_no") val whatsappNo: String,
    @ColumnInfo(name = "button_text") val buttonText: String,
)

data class PrimaryDetailsEntity(
    val experience: String,
    val place: String,
    val salary: String
)

data class JobTagEntity(
    val value: String,
    @ColumnInfo(name = "bg_color") val bgColor: String,
    @ColumnInfo(name = "text_color") val textColor: String
)

// TypeConverter for storing and retrieving a list of JobTagEntity
class JobTagConverter {
    @TypeConverter
    fun fromJobTagList(jobTags: List<JobTagEntity>): String {
        return Gson().toJson(jobTags)
    }

    @TypeConverter
    fun toJobTagList(jobTagsString: String): List<JobTagEntity> {
        val listType = object : TypeToken<List<JobTagEntity>>() {}.type
        return Gson().fromJson(jobTagsString, listType)
    }
}