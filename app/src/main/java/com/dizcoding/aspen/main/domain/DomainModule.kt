package com.dizcoding.aspen.main.domain

import com.dizcoding.aspen.main.data.catalog.CatalogRepositoryImpl
import org.koin.dsl.module

val domainModule = module {
    single<CatalogRepository> { CatalogRepositoryImpl(get()) }
}