package uz.talim.domain.usecase

import uz.talim.domain.model.Assignment
import uz.talim.domain.repository.AssignmentRepository
import uz.talim.util.Resource
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class CreateAssignmentUseCase @Inject constructor(
    private val repository: AssignmentRepository,
) {
    suspend operator fun invoke(
        title: String,
        description: String,
        groupId: String,
        deadline: LocalDateTime,
        maxScore: Int,
    ): Resource<Assignment> {
        if (title.isBlank()) {
            return Resource.Error("Sarlavha bo'sh bo'lishi mumkin emas")
        }

        if (maxScore <= 0) {
            return Resource.Error("Ball musbat son bo'lishi kerak")
        }

        if (deadline.isBefore(LocalDateTime.now())) {
            return Resource.Error("Deadline o'tmishda bo'lishi mumkin emas")
        }

        val formatter = DateTimeFormatter.ISO_DATE_TIME
        val deadlineStr = deadline.format(formatter)

        return repository.createAssignment(
            title = title,
            description = description,
            groupId = groupId,
            deadline = deadlineStr,
            maxScore = maxScore,
        )
    }
}
