package data.db.entity.curiote

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDateTime

@Entity(tableName = "curiotes")
data class CurioteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String?,
    val curiote: String?,
    val toCheck: Boolean,
    val created: LocalDateTime,
    val modified: LocalDateTime,
    val priority: Int
)
