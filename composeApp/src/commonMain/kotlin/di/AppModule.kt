package di

import data.mapper.category.CategoryMapper
import data.mapper.category.CurioteCategoryCombinedMapper
import data.mapper.curiote.CurioteLinkMapper
import data.mapper.curiote.CurioteMapper
import data.repository.CategoryRepositoryImpl
import data.repository.CurioteCategoryCombinedRepositoryImpl
import data.repository.CurioteRepositoryImpl
import domain.repository.CategoryRepository
import domain.repository.CurioteCategoryCombinedRepository
import domain.repository.CurioteRepository
import org.koin.dsl.module

val appModule = module {
    //repo section
    single<CurioteRepository> { CurioteRepositoryImpl(get(), get(), get(), get()) }
    single<CategoryRepository> { CategoryRepositoryImpl(get(), get()) }
    single<CurioteCategoryCombinedRepository> { CurioteCategoryCombinedRepositoryImpl() }

    //mapper section
    single { CurioteMapper(get()) }
    single { CurioteLinkMapper() }
    single { CategoryMapper() }
    single { CurioteCategoryCombinedMapper() }
}
