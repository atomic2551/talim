package uz.talim.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.talim.domain.model.Assignment
import uz.talim.util.Resource

interface AssignmentRepository {
    fun getAssignments(groupId: String): Flow<Resource<List<Assignment>>>

    suspend fun createAssignment(
        title: String,
        description: String,
        groupId: String,
        deadline: String,
        maxScore: Int,
    ): Resource<Assignment>
}
