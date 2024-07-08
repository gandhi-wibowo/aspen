package com.dizcoding.aspen.main.view.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dizcoding.aspen.main.domain.CatalogRepository
import com.dizcoding.aspen.main.view.home.content.MainContent
import com.dizcoding.aspen.main.view.util.FavoriteCatalogHandler
import com.dizcoding.aspen.main.view.util.FavoriteCatalogHandlerImpl
import kotlinx.coroutines.launch

class FavoriteActivityViewModel(
    private val catalogRepository: CatalogRepository
) : ViewModel(),
    FavoriteCatalogHandler by FavoriteCatalogHandlerImpl() {

    private val _searchQuery = MutableLiveData<String>()
    private val _contents = MediatorLiveData<MutableList<Any>>()

    val contents: LiveData<MutableList<Any>> get() = _contents

    fun searchBy(keyword: String?) {
        _searchQuery.value = keyword ?: ""
    }

    init {
        _contents.addSource(_searchQuery) { query ->
            viewModelScope.launch {
                catalogRepository.getFavorite(query).collect {
                    val allCatalogContents: List<MainContent> = it.map {
                        MainContent(
                            catalogId = it.id,
                            catalogName = it.name,
                            catalogImage = it.imageUrl,
                            catalogRate = it.rate.toString(),
                            isCatalogFavorite = it.isFavorite
                        )
                    }
                    _contents.value = allCatalogContents.toMutableList()
                }


            }

        }
    }

    fun updateFavorite(id: Int, isLiked: Boolean) {
        viewModelScope.launch {
            updateFavorite(catalogRepository, id, isLiked)
        }
    }

}