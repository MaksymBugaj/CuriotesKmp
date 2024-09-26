
import domain.repository.CurioteRepository

class CreateCurioteViewModel(
    curioteRepository: CurioteRepository
) : UpsertCurioteBaseViewModel(curioteRepository){

}
