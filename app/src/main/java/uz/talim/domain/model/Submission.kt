package uz.talim.domain.model

import java.time.LocalDateTime

data class Submission(
    val id: String,
    val assignmentId: String,
    val studentId: String,
    val studentName: String,
    val fileUrl: String?,
    val textContent: String?,
    val submittedAt: LocalDateTime,
    val score: Int?,
    val feedback: String?,
)
