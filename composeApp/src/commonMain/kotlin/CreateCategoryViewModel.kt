import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.model.category.Category
import domain.repository.CategoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CreateCategoryViewModel(
    private val categoryRepository: CategoryRepository,
): ViewModel() {

    private val _categoryName = MutableStateFlow("")
    val categoryName: StateFlow<String> = _categoryName

    fun setCategoryName(name: String) {
        _categoryName.value = name
    }

    fun createCategory() {
        viewModelScope.launch {
            categoryRepository.createCategory(
                Category(id = 0, name = categoryName.value)
            )
            _categoryName.value = ""
        }
    }
}