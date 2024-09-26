package data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import data.db.dao.base.BaseDao
import data.db.entity.CurioteEntity
import data.db.entity.full.CurioteFull
import kotlinx.coroutines.flow.Flow

@Dao
interface CurioteDao :BaseDao<CurioteEntity>{

    @Upsert
    suspend fun upsert(curioteEntity: CurioteEntity)

    @Transaction
    @Query("SELECT distinct * FROM curiotes c " +
            "left join curiote_links as cl on c.id = cl.curioteId")
     fun getAllCuriotes(): Flow<List<CurioteFull>>

    @Query("SELECT distinct * FROM curiotes c ")
    fun getOnlyCuriotes(): Flow<List<CurioteEntity>>


    @Transaction
    @Query("Select * from curiotes c inner join curiote_links as cl on c.id == cl.curioteId where c.id = :id")
    suspend fun getCurioteById(id: Int): CurioteFull

    @Transaction
    @Query("" +
            "Select * from curiotes c left join curiote_links as cl on c.id = cl.curioteId where c.title like '%' || :query || '%' or c.curiote like '%' || :query || '%'" +
            "order by " +
            "   case when :sortByDateModified then modified end desc," +
            "   case when :sortByDone then toCheck end desc")
    suspend fun getCurioteByQuery(query: String, sortByDateModified: Boolean, sortByDone: Boolean): List<CurioteFull>
}