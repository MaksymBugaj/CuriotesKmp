import androidx.lifecycle.ViewModel
import domain.repository.CategoryRepository

class CategoryViewModel (
    private val categoryRepository: CategoryRepository
) : ViewModel() {
}