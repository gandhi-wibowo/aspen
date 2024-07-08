package com.dizcoding.aspen.main.view.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dizcoding.aspen.R
import com.dizcoding.aspen.main.domain.CatalogRepository
import com.dizcoding.aspen.main.view.detail.content.detail.Detail
import com.dizcoding.aspen.main.view.detail.content.facility.Facility
import com.dizcoding.aspen.main.view.detail.content.facility.FacilityHolder
import com.dizcoding.aspen.main.view.util.FavoriteCatalogHandler
import com.dizcoding.aspen.main.view.util.FavoriteCatalogHandlerImpl
import kotlinx.coroutines.launch

class DetailCatalogActivityViewModel(
    private val catalogRepository: CatalogRepository
) : ViewModel(),
    FavoriteCatalogHandler by FavoriteCatalogHandlerImpl() {

    fun updateFavorite(id: Int, isLiked: Boolean) {
        viewModelScope.launch {
            updateFavorite(catalogRepository, id, isLiked)
        }
    }

    private val _contents: MutableLiveData<MutableList<Any>> = MutableLiveData()
    fun contents(): LiveData<MutableList<Any>> = _contents
    fun fetchContents(id: Int) {
        viewModelScope.launch {
            val catalog = catalogRepository.getBy(id)
            _contents.value = mutableListOf(
                Detail(
                    catalogId = catalog.catalog.id,
                    catalogName = catalog.catalog.name,
                    catalogImage = catalog.catalog.imageUrl,
                    catalogIsLoved = catalog.catalog.isFavorite,
                    catalogRate = catalog.catalog.rate.toString(),
                    catalogRatedBy = " (${catalog.catalog.ratedBy} Reviews)",
                    catalogShortDescription = catalog.catalog.shortDescription,
                    catalogDescription = catalog.catalog.description
                ),
                FacilityHolder(
                    facilities = listOf(
                        Facility(
                            name = "Dinner",
                            icon = R.drawable.ic_dinner_dining_24
                        ),
                        Facility(
                            name = "Tub",
                            icon = R.drawable.ic_bathtub_24
                        ),
                        Facility(
                            name = "Pool",
                            icon = R.drawable.ic_pool_24
                        ),
                        Facility(
                            name = "No Smoking",
                            icon = R.drawable.ic_smoke_free_24
                        ),
                        Facility(
                            name = "Internet",
                            icon = R.drawable.ic_wifi_24
                        )
                    )
                )
            )
        }
    }
}