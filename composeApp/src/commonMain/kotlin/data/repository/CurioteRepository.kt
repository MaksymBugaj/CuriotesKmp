package data.repository


import androidx.sqlite.SQLiteException
import data.db.dao.CurioteDao
import data.db.dao.CurioteLinkDao
import data.db.entity.CurioteEntity
import data.db.entity.full.CurioteFull
import data.mapper.CurioteLinkMapper
import data.mapper.CurioteMapper
import domain.curiote.Curiote
import domain.repository.CurioteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class CurioteRepositoryImpl(
    private val curioteDao: CurioteDao,
    private val curioteLinkDao: CurioteLinkDao,
    private val curioteMapper: CurioteMapper,
    private val curioteLinkMapper: CurioteLinkMapper
): CurioteRepository {
    override suspend fun upsert(curiote: Curiote) {
        withContext(Dispatchers.IO) {
            try {

                val curioteId = curioteDao.insertReturnId(curioteMapper.mapToData(curiote).curioteEntity)
                val links = curiote.links?.map { link ->
                    curioteLinkMapper.mapToData(link, curioteId)
                }
                println("#NOPE: links:$links")
                links?.let { curioteLinkDao.insert(it) }

            } catch (e: SQLiteException) {
                println("#NOPE: Database error: ${e.message}")
                e.printStackTrace()
            } catch (e: Exception) {
                println("#NOPE: Unexpected error: ${e.message}")
                e.printStackTrace()
            }
        }
    }

    override suspend fun delete(curiote: Curiote) {
        curioteMapper.mapToData(curiote).links?.let { curioteLinkDao.delete(it) }
        curioteDao.delete(curioteMapper.mapToData(curiote).curioteEntity)
    }

    override fun getAllCuriotes(): Flow<List<Curiote>> {
        println("#NOPE : curiotes in repo load start")
        return curioteDao.getAllCuriotes().map {curiotes ->
            curiotes.map { curioteFull: CurioteFull ->
                println("#NOPE curiotes Full $curioteFull")
                curioteMapper.mapToDomain(curioteFull)
            }
        }
    }

    override fun getCurioteEntity(): Flow<List<CurioteEntity>> {
        return curioteDao.getOnlyCuriotes()
    }

    override suspend fun getCurioteById(id: Int): Curiote {
        return curioteMapper.mapToDomain(curioteDao.getCurioteById(id))
    }

    override fun searchCurioteByQuery(query: String, sortByDateModified: Boolean, sortByDone: Boolean): Flow<List<Curiote>> {
        return flow {
            curioteDao.getCurioteByQuery(
                query = query,
                sortByDateModified = sortByDateModified,
                sortByDone = sortByDone
            ).map { curiotes ->
                    curioteMapper.mapToDomain(curiotes)
            }
        }
    }
}