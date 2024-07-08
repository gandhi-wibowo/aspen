package com.dizcoding.aspen.main.data.catalog

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CatalogDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun create(data: List<CatalogEntity>)

    @Query("SELECT * FROM catalog")
    fun getAll(): Flow<List<CatalogEntity>>

    @Query("SELECT * FROM catalog WHERE name LIKE :keyword")
    fun getAll(keyword: String): Flow<List<CatalogEntity>>

    @Query("SELECT * FROM catalog WHERE is_favorite = 1")
    fun getFavorite(): Flow<List<CatalogEntity>>

    @Query("SELECT * FROM catalog WHERE is_favorite = 1 AND name LIKE :keyword")
    fun getFavorite(keyword: String): Flow<List<CatalogEntity>>

    @Query("SELECT * FROM catalog WHERE id =:id")
    suspend fun getBy(id: Int): CatalogDetailEntity

    @Query("DELETE FROM catalog")
    suspend fun deleteAll()

    @Query("UPDATE catalog SET is_favorite = :isLiked WHERE id = :id")
    suspend fun updateFavorite(id: Int, isLiked: Boolean)

}