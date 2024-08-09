package com.aspark.lokalassign.model

import androidx.compose.ui.graphics.Color
import com.aspark.lokalassign.room.JobEntity
import com.aspark.lokalassign.room.JobTagEntity
import com.aspark.lokalassign.room.PrimaryDetailsEntity

// Mapper to convert Job to JobEntity
fun Job.toEntity(): JobEntity {
    return JobEntity(
        jobId = this.id,
        title = this.title,
        companyName = this.companyName,
        primaryDetails = this.primaryDetails?.toEntity() ?: PrimaryDetailsEntity(
            "", "", ""),
        content = this.content,
        jobTags = this.jobTags.map { it.toEntity() },
        whatsappNo = this.whatsappNo,
        buttonText = this.buttonText
    )
}

// Mapper to convert JobEntity to Job
fun JobEntity.toModel(): Job {
    return Job(
        id = this.jobId,
        title = this.title,
        companyName = this.companyName,
        primaryDetails = this.primaryDetails.toModel(),
        content = this.content,
        jobTags = this.jobTags.map { it.toModel() },
        whatsappNo = this.whatsappNo,
        buttonText = this.buttonText
    )
}

// Mapper to convert PrimaryDetails to PrimaryDetailsEntity
fun PrimaryDetails.toEntity(): PrimaryDetailsEntity {
    return PrimaryDetailsEntity(
        experience = this.experience,
        place = this.place,
        salary = this.salary
    )
}

// Mapper to convert PrimaryDetailsEntity to PrimaryDetails
fun PrimaryDetailsEntity.toModel(): PrimaryDetails {
    return PrimaryDetails(
        experience = this.experience,
        place = this.place,
        salary = this.salary,
        jobType = "", // Adjust as needed if you need to handle more fields
        feesCharged = "", // Adjust as needed
        qualification = "" // Adjust as needed
    )
}

// Mapper to convert JobTag to JobTagEntity
fun JobTag.toEntity(): JobTagEntity {
    return JobTagEntity(
        value = this.value,
        bgColor = this.bgColor,
        textColor = this.textColor
    )
}

// Mapper to convert JobTagEntity to JobTag
fun JobTagEntity.toModel(): JobTag {
    return JobTag(
        value = this.value,
        bgColor = this.bgColor,
        textColor = this.textColor
    )
}
