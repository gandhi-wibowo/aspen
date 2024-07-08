package com.dizcoding.aspen.main.view

import com.dizcoding.aspen.main.view.detail.DetailCatalogActivityViewModel
import com.dizcoding.aspen.main.view.favorite.FavoriteActivityViewModel
import com.dizcoding.aspen.main.view.home.HomeActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val viewModule = module {
    viewModelOf(::HomeActivityViewModel)
    viewModelOf(::DetailCatalogActivityViewModel)
    viewModelOf(::FavoriteActivityViewModel)
}