package uz.talim.data.remote.dto

import com.google.gson.annotations.SerializedName
import uz.talim.domain.model.Assignment
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class AssignmentDto(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("group_id") val groupId: String,
    @SerializedName("deadline") val deadline: String,
    @SerializedName("max_score") val maxScore: Int,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("is_submitted") val isSubmitted: Boolean? = false,
    @SerializedName("score") val score: Int? = null,
)

fun AssignmentDto.toDomain(): Assignment {
    val formatter = DateTimeFormatter.ISO_DATE_TIME
    return Assignment(
        id = id,
        title = title,
        description = description,
        groupId = groupId,
        deadline = LocalDateTime.parse(deadline, formatter),
        maxScore = maxScore,
        createdAt = LocalDateTime.parse(createdAt, formatter),
        isSubmitted = isSubmitted ?: false,
        score = score,
    )
}

data class CreateAssignmentRequest(
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("group_id") val groupId: String,
    @SerializedName("deadline") val deadline: String,
    @SerializedName("max_score") val maxScore: Int,
)
