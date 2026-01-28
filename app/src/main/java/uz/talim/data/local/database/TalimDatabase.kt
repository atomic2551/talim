package uz.talim.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import uz.talim.data.local.dao.AssignmentDao
import uz.talim.data.local.entity.AssignmentEntity
import uz.talim.util.Converters

@Database(
    entities = [AssignmentEntity::class],
    version = 1,
    exportSchema = false,
)
@TypeConverters(Converters::class)
abstract class TalimDatabase : RoomDatabase() {
    abstract fun assignmentDao(): AssignmentDao
}
