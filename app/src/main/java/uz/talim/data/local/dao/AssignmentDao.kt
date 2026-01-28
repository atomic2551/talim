package uz.talim.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import uz.talim.data.local.entity.AssignmentEntity

@Dao
interface AssignmentDao {
    @Query("SELECT * FROM assignments WHERE groupId = :groupId ORDER BY deadline ASC")
    fun getAssignmentsByGroup(groupId: String): Flow<List<AssignmentEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAssignments(assignments: List<AssignmentEntity>)

    @Query("DELETE FROM assignments")
    suspend fun deleteAllAssignments()
}
