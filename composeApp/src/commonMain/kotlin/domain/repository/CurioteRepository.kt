package domain.repository

import data.db.entity.CurioteEntity
import domain.curiote.Curiote
import kotlinx.coroutines.flow.Flow

interface CurioteRepository {

    suspend fun upsert(curiote: Curiote)

    suspend fun delete(curiote: Curiote)

    fun getAllCuriotes(): Flow<List<Curiote>>

    fun getCurioteEntity(): Flow<List<CurioteEntity>>

    suspend fun getCurioteById(id: Int): Curiote

    fun searchCurioteByQuery(query: String, sortByDateModified: Boolean, sortByDone: Boolean): Flow<List<Curiote>>
}