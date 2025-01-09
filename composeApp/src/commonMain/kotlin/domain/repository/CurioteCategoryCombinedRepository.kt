package domain.repository

import data.db.entity.full.CurioteCategoryCombinedEntity
import domain.model.category.CurioteCategoryCombined
import kotlinx.coroutines.flow.Flow

interface CurioteCategoryCombinedRepository {

    fun getAllCombined(): Flow<List<CurioteCategoryCombined>>

    fun createCurioteCategoryCombined(model: CurioteCategoryCombined)


}