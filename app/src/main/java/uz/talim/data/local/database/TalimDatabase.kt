package uz.talim.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import uz.talim.data.local.dao.AssignmentDao
import uz.talim.data.local.entity.AssignmentEntity
import uz.talim.util.DateConverters

@Database(
    entities = [AssignmentEntity::class],
    version = 1,
    exportSchema = false,
)
@TypeConverters(DateConverters::class)
abstract class TalimDatabase : RoomDatabase() {
    abstract fun assignmentDao(): AssignmentDao
}
