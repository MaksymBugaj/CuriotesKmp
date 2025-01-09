package domain.repository

import domain.model.category.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    suspend fun createCategory(category: Category)

    suspend fun updateCategory(category: Category)

    suspend fun deleteCategory(category: Category)

    fun getCategories(): Flow<List<Category>>

    suspend fun getCategory(id: Int): Category

    fun searchCategory(query: String): Flow<List<Category>>
}