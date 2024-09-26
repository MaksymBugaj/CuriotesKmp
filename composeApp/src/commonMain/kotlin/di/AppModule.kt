package di

import data.mapper.CurioteLinkMapper
import data.mapper.CurioteMapper
import data.repository.CurioteRepositoryImpl
import domain.repository.CurioteRepository
import org.koin.dsl.module

val appModule = module {
    //repo section
    single<CurioteRepository> { CurioteRepositoryImpl(get(), get(), get(), get()) }

    //mapper section
    single { CurioteMapper(get()) }
    single { CurioteLinkMapper()}
}
