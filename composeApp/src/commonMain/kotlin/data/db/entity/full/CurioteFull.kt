package data.db.entity.full

import androidx.room.Embedded
import androidx.room.Relation
import data.db.entity.CurioteEntity
import data.db.entity.CurioteLinkEntity

data class CurioteFull(
    @Embedded
    val curioteEntity: CurioteEntity,
    @Relation(parentColumn = "id", entityColumn = "curioteId", entity = CurioteLinkEntity::class)
    val links: List<CurioteLinkEntity>?
)
