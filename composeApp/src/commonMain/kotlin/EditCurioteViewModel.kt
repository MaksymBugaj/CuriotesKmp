
import androidx.lifecycle.viewModelScope
import domain.curiote.Curiote
import domain.repository.CurioteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class EditCurioteViewModel(
    private val curioteRepository: CurioteRepository
) : UpsertCurioteBaseViewModel(curioteRepository) {

    private val _curiote = MutableStateFlow<Curiote?>(null)
    val curiote = _curiote.asStateFlow()


    fun restoreCuriote(curioteId: Int) {
        viewModelScope.launch {
            println("#NOPE: EditCurioteViewModel Launched ")
            curioteRepository.getCurioteById(curioteId).let { curiote ->
                println("#NOPE: EditCurioteViewModel got curiote: $curiote")
                _curiote.update { curiote }
                curiote.title?.let { setCurioteTitle(it) }
                curiote.curiote?.let { setCurioteDescription(it) }
                setDetailedExplanation(curiote.toCheck)
            }
        }
    }

    fun deleteCuriote(curioteModel: Curiote){
        viewModelScope.launch {
            curioteRepository.delete(curioteModel)
        }
    }

    override fun saveCuriote() {
        if(validate()) {
            val curioteToSave = _curiote.value?.copy(
                title = curioteTitle.value,
                curiote = curioteDescription.value,
                toCheck = needsDetailedExplanation.value,
                modified = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
            )

            println("#NOPE: EditCurioteViewModel saving edited curiote $curioteToSave")
            curioteToSave?.let {
                viewModelScope.launch {
                    curioteRepository.upsert(it)
                    clearFields()
                }
            }

        }
    }

    override fun onCleared() {
        super.onCleared()
        println("#NOPE: EditCurioteViewModel onCleared")
        clearFields()

    }
}