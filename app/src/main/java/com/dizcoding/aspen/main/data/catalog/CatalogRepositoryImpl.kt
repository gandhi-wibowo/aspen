package com.dizcoding.aspen.main.data.catalog

import com.dizcoding.aspen.main.domain.CatalogRepository
import kotlinx.coroutines.flow.Flow

class CatalogRepositoryImpl(
    private val catalogDao: CatalogDao
) : CatalogRepository {
    override suspend fun create(data: List<CatalogEntity>) {
        return catalogDao.create(data)
    }

    override suspend fun getBy(id: Int): CatalogDetailEntity {
        return catalogDao.getBy(id)
    }

    override suspend fun deleteAll() {
        return catalogDao.deleteAll()
    }

    override suspend fun updateFavorite(id: Int, isLiked: Boolean) {
        return catalogDao.updateFavorite(id, isLiked)
    }

    override fun getAll(keyword: String, isLiked: Boolean): Flow<List<CatalogEntity>> {
        if (keyword != "") {
            return if (isLiked) catalogDao.getFavorite("%$keyword%")
            else catalogDao.getAll("%$keyword%")
        } else {
            return catalogDao.getAll()
        }
    }

    override fun getFavorite(keyword: String): Flow<List<CatalogEntity>> {
        if (keyword != "") {
            return catalogDao.getFavorite("%$keyword%")
        } else {
            return catalogDao.getFavorite()
        }
    }
}