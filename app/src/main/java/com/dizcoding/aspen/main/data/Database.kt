package com.dizcoding.aspen.main.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dizcoding.aspen.main.data.catalog.CatalogDao
import com.dizcoding.aspen.main.data.catalog.CatalogEntity
import com.dizcoding.aspen.main.data.facility.FacilityEntity

@Database(
    version = 2,
    entities = [
        CatalogEntity::class,
        FacilityEntity::class
    ]
)
abstract class Database : RoomDatabase() {
    abstract fun catalogDao(): CatalogDao
}