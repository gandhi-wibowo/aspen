package com.dizcoding.aspen.main.domain

import com.dizcoding.aspen.main.data.catalog.CatalogDetailEntity
import com.dizcoding.aspen.main.data.catalog.CatalogEntity
import kotlinx.coroutines.flow.Flow

interface CatalogRepository {
    suspend fun create(data: List<CatalogEntity>)
    suspend fun getBy(id: Int): CatalogDetailEntity
    suspend fun deleteAll()
    suspend fun updateFavorite(id: Int, isLiked: Boolean)
    fun getAll(keyword: String): Flow<List<CatalogEntity>>
    fun getFavorite(keyword: String): Flow<List<CatalogEntity>>

}