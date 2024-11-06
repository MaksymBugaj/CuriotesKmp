package data.db.dao.category

import androidx.room.Dao
import data.db.dao.base.BaseDao
import data.db.entity.category.CurioteCategoryEntity

@Dao
interface CategoryDao : BaseDao<CurioteCategoryEntity> {
}