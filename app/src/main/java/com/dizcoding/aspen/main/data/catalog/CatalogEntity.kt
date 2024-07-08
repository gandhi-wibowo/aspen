package com.dizcoding.aspen.main.data.catalog

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "catalog")
data class CatalogEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "short_desc") val shortDescription: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "rate") val rate: Double,
    @ColumnInfo(name = "rated_by") val ratedBy: Int,
    @ColumnInfo(name = "image_url") val imageUrl: String,
    @ColumnInfo(name = "is_favorite") val isFavorite: Boolean
)