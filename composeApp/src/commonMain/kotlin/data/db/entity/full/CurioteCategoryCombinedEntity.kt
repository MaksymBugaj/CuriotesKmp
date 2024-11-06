package data.db.entity.full

import androidx.room.Entity

@Entity(
    tableName = "curiote_category_combined",
    primaryKeys = [
        "curioteId", "curioteCategoryId"
    ]
)
data class CurioteCategoryCombinedEntity(
    val curioteId: Long,
    val curioteCategoryId: Long
)
