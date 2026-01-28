package uz.talim.data.remote.dto

import com.google.gson.annotations.SerializedName
import uz.talim.domain.model.StudentRank

data class LeaderboardItemDto(
    @SerializedName("student_id") val studentId: String,
    @SerializedName("student_name") val studentName: String,
    @SerializedName("total_score") val totalScore: Int,
    @SerializedName("completed_assignments") val completedAssignments: Int,
    @SerializedName("total_assignments") val totalAssignments: Int,
)

fun LeaderboardItemDto.toDomain(rank: Int): StudentRank {
    return StudentRank(
        rank = rank,
        studentId = studentId,
        studentName = studentName,
        totalScore = totalScore,
        completedAssignments = completedAssignments,
        totalAssignments = totalAssignments,
    )
}
