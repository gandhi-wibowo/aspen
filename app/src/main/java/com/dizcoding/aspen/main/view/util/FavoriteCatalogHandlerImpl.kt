package com.dizcoding.aspen.main.view.util

import com.dizcoding.aspen.main.domain.CatalogRepository

class FavoriteCatalogHandlerImpl : FavoriteCatalogHandler {

    override suspend fun updateFavorite(
        repo: CatalogRepository,
        id: Int,
        isLiked: Boolean
    ) {
        repo.updateFavorite(id, isLiked)
    }

}