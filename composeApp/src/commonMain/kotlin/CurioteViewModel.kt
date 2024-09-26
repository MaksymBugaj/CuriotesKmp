import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.db.dao.CurioteDao
import data.db.entity.CurioteEntity
import domain.curiote.Curiote
import domain.repository.CurioteRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.KoinApplication.Companion.init

class CurioteViewModel(
    private val curioteRepository: CurioteRepository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")

    private val _sortByDateModified = MutableStateFlow(false)
    val sortByDateModified: StateFlow<Boolean> = _sortByDateModified
    private val _sortByDone = MutableStateFlow(false)
    val sortByDone: StateFlow<Boolean> = _sortByDone

//    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
//    val curiotesTest : StateFlow<List<Curiote>> = _searchQuery
//        .debounce(300)
//        .distinctUntilChanged()
//        .flatMapLatest { query ->
//            curioteRepository.searchCurioteByQuery(query)
//        }
//        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    private val _curiotes = MutableStateFlow<List<Curiote>>(emptyList())
    val curiotes: StateFlow<List<Curiote>> = _curiotes.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText.asStateFlow()

    private val _isSearchActive = MutableStateFlow(false)

    val isSearchActive = combine(
        _searchText,
        _isSearchActive
    ) { searchText, isSearchActive ->
        searchText.isNotBlank() || isSearchActive
    }

    init {
        getAllCuriotes()
    }

    fun updateSortOptions(sortByDate: Boolean, sortByDone: Boolean) {
        _sortByDateModified.value = sortByDate
        _sortByDone.value = sortByDone
        if(sortByDone) {
            _sortByDateModified.value = false
        } else if(sortByDate) {
            _sortByDone.value = false
        }
    }

    fun getAllCuriotes() {
        viewModelScope.launch {
            curioteRepository.getAllCuriotes().collect { curiotes ->
                println("#NOPE CurioteViewModel:getAllCuriotes Curiotes: $curiotes")
                _curiotes.update { curiotes }
            }
        }
    }

    fun setSearchValue(value: String) {
        _searchText.update { value }
        _searchQuery.value = value
        curioteRepository.searchCurioteByQuery(
            query = value,
            sortByDateModified = _sortByDateModified.value,
            sortByDone = _sortByDone.value
        ).onEach { curiotes ->
            println("#NOPE: update: $curiotes")
            _curiotes.update { curiotes }
        }.launchIn(viewModelScope)
    }

    fun onToggleChanged() {
        _isSearchActive.update { !_isSearchActive.value }
        setSearchValue("")
    }
}