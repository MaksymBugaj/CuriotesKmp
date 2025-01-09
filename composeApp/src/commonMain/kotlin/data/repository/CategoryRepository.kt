package data.repository

import data.db.dao.category.CategoryDao
import data.mapper.category.CategoryMapper
import domain.model.category.Category
import domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CategoryRepositoryImpl (
    private val categoryDao: CategoryDao,
    private val categoryMapper: CategoryMapper
): CategoryRepository {
    override suspend fun createCategory(category: Category) {
        categoryDao.insert(categoryMapper.mapToData(category))
    }

    override suspend fun updateCategory(category: Category) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCategory(category: Category) {
        TODO("Not yet implemented")
    }

    override fun getCategories(): Flow<List<Category>> =
        categoryDao.getAllCategories().map { categories ->
            categories.map {category ->
                categoryMapper.mapToDomain(category)
            }
        }


    override suspend fun getCategory(id: Int): Category {
        TODO("Not yet implemented")
    }

    override fun searchCategory(query: String): Flow<List<Category>> {
        TODO("Not yet implemented")
    }

}