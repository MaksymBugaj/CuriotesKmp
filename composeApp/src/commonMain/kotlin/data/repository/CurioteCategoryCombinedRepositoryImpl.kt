package data.repository

import domain.model.category.CurioteCategoryCombined
import domain.repository.CurioteCategoryCombinedRepository
import kotlinx.coroutines.flow.Flow

class CurioteCategoryCombinedRepositoryImpl(

) : CurioteCategoryCombinedRepository{
    override fun getAllCombined(): Flow<List<CurioteCategoryCombined>> {
        TODO("Not yet implemented")
    }

    override fun createCurioteCategoryCombined(model: CurioteCategoryCombined) {
        TODO("Not yet implemented")
    }
}