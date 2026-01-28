package uz.talim.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.talim.domain.model.Assignment
import java.time.LocalDateTime

@Entity(tableName = "assignments")
data class AssignmentEntity(
    @PrimaryKey val id: String,
    val title: String,
    val description: String,
    val groupId: String,
    val deadline: LocalDateTime,
    val maxScore: Int,
    val createdAt: LocalDateTime,
    val isSubmitted: Boolean,
    val score: Int?,
)

fun AssignmentEntity.toDomain(): Assignment {
    return Assignment(
        id = id,
        title = title,
        description = description,
        groupId = groupId,
        deadline = deadline,
        maxScore = maxScore,
        createdAt = createdAt,
        isSubmitted = isSubmitted,
        score = score,
    )
}

fun Assignment.toEntity(): AssignmentEntity {
    return AssignmentEntity(
        id = id,
        title = title,
        description = description,
        groupId = groupId,
        deadline = deadline,
        maxScore = maxScore,
        createdAt = createdAt,
        isSubmitted = isSubmitted,
        score = score,
    )
}
