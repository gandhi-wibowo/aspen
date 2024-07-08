package com.dizcoding.aspen.main.view.util

import com.dizcoding.aspen.main.domain.CatalogRepository

interface FavoriteCatalogHandler {
    suspend fun updateFavorite(repo: CatalogRepository, id: Int, isLiked: Boolean)
}