import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

actual val viewModelModule = module {
    viewModelOf(::CreateCurioteViewModel)
    viewModelOf(::CurioteViewModel)
    viewModelOf(::EditCurioteViewModel)
    viewModelOf(::CategoryViewModel)
    viewModelOf(::CreateCategoryViewModel)
}