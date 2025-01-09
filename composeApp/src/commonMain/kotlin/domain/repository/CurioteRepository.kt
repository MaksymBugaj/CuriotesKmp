package domain.repository

import data.db.entity.curiote.CurioteEntity
import domain.model.curiote.Curiote
import kotlinx.coroutines.flow.Flow

interface CurioteRepository {

    suspend fun upsert(curiote: Curiote)

    suspend fun delete(curiote: Curiote)

    fun getAllCuriotes(): Flow<List<Curiote>>

    fun getCurioteEntity(): Flow<List<CurioteEntity>>

    suspend fun getCurioteById(id: Long): Curiote

    fun searchCurioteByQuery(query: String, sortByDateModified: Boolean, sortByDone: Boolean): Flow<List<Curiote>>
}