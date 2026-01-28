package uz.talim.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.talim.domain.model.Assignment
import uz.talim.domain.repository.AssignmentRepository
import uz.talim.util.Resource
import javax.inject.Inject

class GetAssignmentsUseCase @Inject constructor(
    private val repository: AssignmentRepository,
) {
    operator fun invoke(groupId: String): Flow<Resource<List<Assignment>>> {
        return repository.getAssignments(groupId)
    }
}
