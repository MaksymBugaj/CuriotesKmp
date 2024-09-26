package data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.benasher44.uuid.Uuid
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
)
