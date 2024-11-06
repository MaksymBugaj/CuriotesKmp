package data.db.entity.curiote

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "curiote_links",
    foreignKeys = [
        androidx.room.ForeignKey(
            entity = CurioteEntity::class,
            parentColumns = ["id"],
            childColumns = ["curioteId"],
            onDelete = androidx.room.ForeignKey.CASCADE
        )
    ]
)
data class CurioteLinkEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val curioteLink: String,
    val curioteId: Long
)
