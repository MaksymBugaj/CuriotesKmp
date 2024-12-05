import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.model.category.Category
import domain.model.category.CurioteCategoryCombined
import domain.model.curiote.Curiote
import domain.repository.CategoryRepository
import domain.repository.CurioteCategoryCombinedRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.core.KoinApplication.Companion.init
import ui.category.CategoriesViewState

/**
 * Categories is a grid,
 * each item has a title and a count of items in the category
 */
class CategoryViewModel (
    private val categoryRepository: CategoryRepository,
    private val curioteCategoryCombinedRepository: CurioteCategoryCombinedRepository
) : ViewModel() {

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>> = _categories

    private val _combinedCuriotesIds = MutableStateFlow<List<CurioteCategoryCombined>>(emptyList())
    val combinedCuriotesIds: StateFlow<List<CurioteCategoryCombined>> = _combinedCuriotesIds

    private val _curiotesCombined = MutableStateFlow<List<Pair<Category, List<Curiote>>>>(emptyList())
    val curiotesCombined: StateFlow<List<Pair<Category, List<Curiote>>>> = _curiotesCombined

    private val _uiState = MutableStateFlow<CategoriesViewState>(CategoriesViewState.EmptyCategoriesState)
    val uiState: StateFlow<CategoriesViewState> = _uiState

    init {
        loadAllCategories()
    }

    /**
     * load all categories, then load all curiotes for each category
     */
    private fun loadAllCategories() {
        viewModelScope.launch {
            println("#NOPE: GET CATEGORIES")
            categoryRepository.getCategories().collect { categories ->
                if(categories.isEmpty()){
                    updateUiState(CategoriesViewState.EmptyCategoriesState)
                } else {
                    updateUiState(CategoriesViewState.DisplayCategoriesView(categories))
                }
                _categories.value = categories
                println("#NOPE: GET CATEGORIES:: $categories")
                //fixme -> loadCategoryCurioteCombined()
            }
        }
    }

    private fun loadCategoryCurioteCombined() {
        viewModelScope.launch {
            curioteCategoryCombinedRepository.getAllCombined().onEach { combined ->
                _combinedCuriotesIds.value = combined
            }
        }
    }

    private fun loadCombinedCuriotes(combinedIds: List<CurioteCategoryCombined>) {
        viewModelScope.launch {
            //todo getList of Pairs :)

        }

    }

    private fun updateUiState(value: CategoriesViewState){
        _uiState.value = value
    }

}