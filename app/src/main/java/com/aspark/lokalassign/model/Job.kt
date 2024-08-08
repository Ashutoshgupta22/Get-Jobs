package com.aspark.lokalassign.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

class JobResponse(
    @SerializedName("results")
    val results: List<Job>
)

@Parcelize
data class Job(
    @SerializedName("id")
    val id: Int =  0,

    @SerializedName("title")
    val title: String = "",

    @SerializedName("type")
    val type: Int = 0,

    @SerializedName("primary_details")
    val primaryDetails: PrimaryDetails? = null,

//    @SerializedName("fee_details")
//    val feeDetails: FeeDetails,

    @SerializedName("job_tags")
    val jobTags: List<JobTag> = emptyList(),

    @SerializedName("job_type")
    val jobType: Int = 0,

    @SerializedName("job_category_id")
    val jobCategoryId: Int = 0,

    @SerializedName("qualification")
    val qualification: Int = 0,

    @SerializedName("experience")
    val experience: Int = 0,

    @SerializedName("shift_timing")
    val shiftTiming: Int = 0,

    @SerializedName("job_role_id")
    val jobRoleId: Int = 0,

    @SerializedName("salary_max")
    val salaryMax: Int? = 0,

    @SerializedName("salary_min")
    val salaryMin: Int? = 0,

    @SerializedName("city_location")
    val cityLocation: Int = 0,

    @SerializedName("locality")
    val locality: Int = 0,

    @SerializedName("premium_till")
    val premiumTill: String? = "",

    @SerializedName("content")
    val content: String = "",

    @SerializedName("company_name")
    val companyName: String = "",

    @SerializedName("advertiser")
    val advertiser: Int = 0,

    @SerializedName("button_text")
    val buttonText: String = "",

    @SerializedName("custom_link")
    val customLink: String = "",

    @SerializedName("amount")
    val amount: String  ="",

    @SerializedName("views")
    val views: Int = 0,

    @SerializedName("shares")
    val shares: Int = 0,

    @SerializedName("fb_shares")
    val fbShares: Int = 0,

    @SerializedName("is_bookmarked")
    val isBookmarked: Boolean = false,

    @SerializedName("is_applied")
    val isApplied: Boolean = false,

    @SerializedName("is_owner")
    val isOwner: Boolean = false,

    @SerializedName("updated_on")
    val updatedOn: String = "",

    @SerializedName("whatsapp_no")
    val whatsappNo: String = "",

    @SerializedName("contact_preference")
    val contactPreference: ContactPreference? = null,

    @SerializedName("created_on")
    val createdOn: String = "",

    @SerializedName("is_premium")
    val isPremium: Boolean = false,

    @SerializedName("creatives")
    val creatives: List<Creative> = emptyList(),

//    @SerializedName("videos")
//    val videos: List<Any>,

    @SerializedName("locations")
    val locations: List<Location> = emptyList(),

//    @SerializedName("tags")
//    val tags: List<Any>,

    @SerializedName("content_v3")
    val contentV3: ContentV3? = null,

    @SerializedName("status")
    val status: Int = 0,

    @SerializedName("expire_on")
    val expireOn: String? = "",

    @SerializedName("job_hours")
    val jobHours: String = "",

    @SerializedName("openings_count")
    val openingsCount: Int = 0,

    @SerializedName("job_role")
    val jobRole: String = "",

    @SerializedName("other_details")
    val otherDetails: String = "",

    @SerializedName("job_category")
    val jobCategory: String = "",

    @SerializedName("num_applications")
    val numApplications: Int = 0,

    @SerializedName("enable_lead_collection")
    val enableLeadCollection: Boolean = false,

    @SerializedName("is_job_seeker_profile_mandatory")
    val isJobSeekerProfileMandatory: Boolean = false,

    @SerializedName("job_location_slug")
    val jobLocationSlug: String = "",

    @SerializedName("fees_text")
    val feesText: String? = "",

    @SerializedName("question_bank_id")
    val questionBankId: Int? = 0,

    @SerializedName("screening_retry")
    val screeningRetry: Int? = 0,

    @SerializedName("should_show_last_contacted")
    val shouldShowLastContacted: Boolean = false,

    @SerializedName("fees_charged")
    val feesCharged: Int = 0
): Parcelable

@Parcelize
data class PrimaryDetails(
    @SerializedName("Place")
    val place: String,

    @SerializedName("Salary")
    val salary: String,

    @SerializedName("job_type")
    val jobType: String,

    @SerializedName("experience")
    val experience: String,

    @SerializedName("fees_charged")
    val feesCharged: String,

    @SerializedName("qualification")
    val qualification: String
): Parcelable

//@Parcelize
//data class FeeDetails(
//    @SerializedName("v3")
////    val v3: List<Any>
//): Parcelable

@Parcelize
data class JobTag(
    @SerializedName("value")
    val value: String,

    @SerializedName("bg_color")
    val bgColor: String,

    @SerializedName("text_color")
    val textColor: String
): Parcelable

@Parcelize
data class ContactPreference(
    @SerializedName("preference")
    val preference: Int,

    @SerializedName("whatsapp_link")
    val whatsappLink: String,

    @SerializedName("preferred_call_start_time")
    val preferredCallStartTime: String,

    @SerializedName("preferred_call_end_time")
    val preferredCallEndTime: String
): Parcelable

@Parcelize
data class Creative(
    @SerializedName("file")
    val file: String,

    @SerializedName("thumb_url")
    val thumbUrl: String,

    @SerializedName("creative_type")
    val creativeType: Int
): Parcelable

@Parcelize
data class Location(
    @SerializedName("id")
    val id: Int,

    @SerializedName("locale")
    val locale: String,

    @SerializedName("state")
    val state: Int
): Parcelable

@Parcelize
data class ContentV3(
    @SerializedName("v3")
    val v3: List<V3>
): Parcelable

@Parcelize
data class V3(
    @SerializedName("field_key")
    val fieldKey: String,

    @SerializedName("field_name")
    val fieldName: String,

    @SerializedName("field_value")
    val fieldValue: String
): Parcelable
