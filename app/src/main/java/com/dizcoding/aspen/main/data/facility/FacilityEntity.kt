package com.dizcoding.aspen.main.data.facility

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "facility")
data class FacilityEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "catalog_id") val catalogId: Int,
    @ColumnInfo(name = "name") val name: String,
)