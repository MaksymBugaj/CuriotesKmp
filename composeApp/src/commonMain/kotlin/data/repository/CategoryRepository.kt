package data.repository

import data.db.dao.category.CategoryDao
import data.mapper.category.CategoryMapper
import domain.model.category.Category
import domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow

class CategoryRepositoryImpl (
    categoryDao: CategoryDao,
    categoryMapper: CategoryMapper
): CategoryRepository {
    override fun createCategory(category: Category) {
        TODO("Not yet implemented")
    }

    override fun updateCategory(category: Category) {
        TODO("Not yet implemented")
    }

    override fun deleteCategory(category: Category) {
        TODO("Not yet implemented")
    }

    override fun getCategories(): Flow<List<Category>> {
        TODO("Not yet implemented")
    }

    override suspend fun getCategory(id: Int): Category {
        TODO("Not yet implemented")
    }

    override fun searchCategory(query: String): Flow<List<Category>> {
        TODO("Not yet implemented")
    }

}