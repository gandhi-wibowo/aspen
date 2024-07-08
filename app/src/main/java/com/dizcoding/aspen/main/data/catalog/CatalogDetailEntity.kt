package com.dizcoding.aspen.main.data.catalog

import androidx.room.Embedded
import androidx.room.Relation
import com.dizcoding.aspen.main.data.facility.FacilityEntity

class CatalogDetailEntity(
    @Embedded val catalog: CatalogEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "catalog_id"
    )
    val facilityEntity: List<FacilityEntity>
)