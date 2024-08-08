package com.aspark.lokalassign.model

class JobResponse(
    val results: List<Job>
)

data class Job(
    val id: Int,
    val title: String,
    val type: Int,
    val primaryDetails: PrimaryDetails,
    val feeDetails: FeeDetails,
    val jobTags: List<JobTag>,
    val jobType: Int,
    val jobCategoryId: Int,
    val qualification: Int,
    val experience: Int,
    val shiftTiming: Int,
    val jobRoleId: Int,
    val salaryMax: Int?,
    val salaryMin: Int?,
    val cityLocation: Int,
    val locality: Int,
    val premiumTill: String?,
    val content: String,
    val companyName: String,
    val advertiser: Int,
    val buttonText: String,
    val customLink: String,
    val amount: String,
    val views: Int,
    val shares: Int,
    val fbShares: Int,
    val isBookmarked: Boolean,
    val isApplied: Boolean,
    val isOwner: Boolean,
    val updatedOn: String,
    val whatsappNo: String,
    val contactPreference: ContactPreference,
    val createdOn: String,
    val isPremium: Boolean,
    val creatives: List<Creative>,
    val videos: List<Any>,
    val locations: List<Location>,
    val tags: List<Any>,
    val contentV3: ContentV3,
    val status: Int,
    val expireOn: String?,
    val jobHours: String,
    val openingsCount: Int,
    val jobRole: String,
    val otherDetails: String,
    val jobCategory: String,
    val numApplications: Int,
    val enableLeadCollection: Boolean,
    val isJobSeekerProfileMandatory: Boolean,
//    val translatedContent: TranslatedContent?,
    val jobLocationSlug: String,
    val feesText: String?,
    val questionBankId: Int?,
    val screeningRetry: Int?,
    val shouldShowLastContacted: Boolean,
    val feesCharged: Int
)

data class PrimaryDetails(
    val place: String,
    val salary: String,
    val jobType: String,
    val experience: String,
    val feesCharged: String,
    val qualification: String
)

data class FeeDetails(
    val v3: List<Any>
)

data class JobTag(
    val value: String,
    val bgColor: String,
    val textColor: String
)

data class ContactPreference(
    val preference: Int,
    val whatsappLink: String,
    val preferredCallStartTime: String,
    val preferredCallEndTime: String
)

data class Creative(
    val file: String,
    val thumbUrl: String,
    val creativeType: Int
)

data class Location(
    val id: Int,
    val locale: String,
    val state: Int
)

data class ContentV3(
    val v3: List<V3>
)

data class V3(
    val fieldKey: String,
    val fieldName: String,
    val fieldValue: String
)

