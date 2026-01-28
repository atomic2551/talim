package uz.talim.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.talim.data.local.dao.AssignmentDao
import uz.talim.data.local.entity.toDomain
import uz.talim.data.local.entity.toEntity
import uz.talim.data.remote.api.TalimApiService
import uz.talim.data.remote.dto.CreateAssignmentRequest
import uz.talim.data.remote.dto.toDomain
import uz.talim.domain.model.Assignment
import uz.talim.domain.repository.AssignmentRepository
import uz.talim.util.Resource
import javax.inject.Inject

class AssignmentRepositoryImpl @Inject constructor(
    private val api: TalimApiService,
    private val dao: AssignmentDao,
) : AssignmentRepository {
    override fun getAssignments(groupId: String): Flow<Resource<List<Assignment>>> = flow {
        emit(Resource.Loading())
        val localAssignments = dao.getAssignmentsByGroup(groupId)
        localAssignments.collect { entities ->
            emit(Resource.Loading(data = entities.map { it.toDomain() }))
        }

        try {
            val response = api.getAssignments(groupId)
            if (response.isSuccessful) {
                response.body()?.let { dtos ->
                    val assignments = dtos.map { it.toDomain() }
                    dao.deleteAllAssignments()
                    dao.insertAssignments(assignments.map { it.toEntity() })
                    emit(Resource.Success(assignments))
                }
            } else {
                emit(Resource.Error("Xatolik: ${response.message()}"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Internet aloqasi yo'q: ${e.localizedMessage}"))
        }
    }

    override suspend fun createAssignment(
        title: String,
        description: String,
        groupId: String,
        deadline: String,
        maxScore: Int,
    ): Resource<Assignment> {
        return try {
            val request = CreateAssignmentRequest(
                title = title,
                description = description,
                groupId = groupId,
                deadline = deadline,
                maxScore = maxScore,
            )
            val response = api.createAssignment(request)
            if (response.isSuccessful) {
                response.body()?.let { dto ->
                    val assignment = dto.toDomain()
                    dao.insertAssignments(listOf(assignment.toEntity()))
                    Resource.Success(assignment)
                } ?: Resource.Error("Ma'lumot topilmadi")
            } else {
                Resource.Error("Xatolik: ${response.message()}")
            }
        } catch (e: Exception) {
            Resource.Error("Xatolik: ${e.localizedMessage}")
        }
    }
}
