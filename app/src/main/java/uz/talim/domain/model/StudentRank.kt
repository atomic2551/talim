package uz.talim.domain.model

data class StudentRank(
    val rank: Int,
    val studentId: String,
    val studentName: String,
    val totalScore: Int,
    val completedAssignments: Int,
    val totalAssignments: Int,
) {
    val completionPercentage: Int
        get() = if (totalAssignments > 0) {
            (completedAssignments * 100) / totalAssignments
        } else 0
}
