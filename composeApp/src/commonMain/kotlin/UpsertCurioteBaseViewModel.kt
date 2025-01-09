import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.model.curiote.Curiote
import domain.model.curiote.curioteLink.CurioteLink
import domain.repository.CurioteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

abstract class UpsertCurioteBaseViewModel(
    private val curioteRepository: CurioteRepository,
) : ViewModel() {
    private val _curioteTitle = MutableStateFlow("")
    val curioteTitle: StateFlow<String> = _curioteTitle

    private val _curioteDescription = MutableStateFlow("")
    val curioteDescription: StateFlow<String> = _curioteDescription

    private val _curioteLinks = MutableStateFlow<List<String>>(emptyList())
    val curioteLinks: StateFlow<List<String>> = _curioteLinks
    private val linksHelper: MutableList<String> = mutableListOf()

    private val linksHelper2: MutableMap<Int, String> = mutableMapOf()

    private val _needsDetailedExplanation = MutableStateFlow(false)
    val needsDetailedExplanation: StateFlow<Boolean> = _needsDetailedExplanation

    //todo to be changed to the links
    private val _curioteLink = MutableStateFlow("")
    val curioteLink: StateFlow<String> = _curioteLink


    //region SET

    fun setCurioteTitle(value: String) {
        _curioteTitle.update { value }
    }

    fun setCurioteDescription(value: String) {
        _curioteDescription.update { value }
    }

    fun setLinksHelper(value: String, index: Int) {
        //linksHelper.addAll(value)
        linksHelper2[index] = value
    }

    fun setCurioteLink(value: String) {
        _curioteLink.value = value
    }

    fun setDetailedExplanation(value: Boolean) {
        _needsDetailedExplanation.update { value }
    }


    //end region SET

    //region SAVE

    open fun saveCuriote() {
        if (validate()) {
            val curiote = Curiote(
                id = 0,
                title = _curioteTitle.value,
                curiote = _curioteDescription.value,
                toCheck = _needsDetailedExplanation.value,
                created = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                modified = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                links = listOf(
                    CurioteLink(
                        id = 0L,
                        link = _curioteLink.value
                    )
                ),
                priority = 0
            )

            viewModelScope.launch {
                curioteRepository.upsert(curiote)
                clearFields()
            }
        }
    }

    fun validate(): Boolean {
        return _curioteTitle.value.isNotEmpty() ||
                _curioteDescription.value.isNotEmpty()
    }

    fun clearFields() {
        setCurioteTitle("")
        setCurioteDescription("")
        setCurioteLink("")
        setCurioteLink("")
        setDetailedExplanation(value = false)
    }
}