package data.db.dao.category

import androidx.room.Dao
import androidx.room.Query
import data.db.dao.base.BaseDao
import data.db.entity.category.CurioteCategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao : BaseDao<CurioteCategoryEntity> {

    @Query("SELECT * FROM curiote_categories")
    fun getAllCategories(): Flow<List<CurioteCategoryEntity>>
}