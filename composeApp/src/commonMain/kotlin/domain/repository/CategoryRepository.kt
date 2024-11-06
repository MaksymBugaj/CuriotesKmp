package domain.repository

import domain.model.category.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    fun createCategory(category: Category)

    fun updateCategory(category: Category)

    fun deleteCategory(category: Category)

    fun getCategories(): Flow<List<Category>>

    suspend fun getCategory(id: Int): Category

    fun searchCategory(query: String): Flow<List<Category>>
}