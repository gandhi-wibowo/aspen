package com.dizcoding.aspen.main.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dizcoding.aspen.main.data.catalog.CatalogEntity
import com.dizcoding.aspen.main.domain.CatalogRepository
import com.dizcoding.aspen.main.view.home.content.MainContent
import com.dizcoding.aspen.main.view.home.content.popular.MainPopularHolder
import com.dizcoding.aspen.main.view.home.content.recommended.MainRecommendHolder
import com.dizcoding.aspen.main.view.util.FavoriteCatalogHandler
import com.dizcoding.aspen.main.view.util.FavoriteCatalogHandlerImpl
import kotlinx.coroutines.launch

class HomeActivityViewModel(
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
                catalogRepository.getAll(query).collect {
                    val allCatalogContents: List<MainContent> = it.map {
                        MainContent(
                            catalogId = it.id,
                            catalogName = it.name,
                            catalogImage = it.imageUrl,
                            catalogRate = it.rate.toString(),
                            isCatalogFavorite = it.isFavorite
                        )
                    }
                    val catalogContent: List<MainContent> =
                        it.filter { it.category == "popular" }.map {
                            MainContent(
                                catalogId = it.id,
                                catalogName = it.name,
                                catalogImage = it.imageUrl,
                                catalogRate = it.rate.toString(),
                                isCatalogFavorite = it.isFavorite
                            )
                        }


                    if (catalogContent.isEmpty() && allCatalogContents.isEmpty()) _contents.value =
                        mutableListOf()
                    else if (catalogContent.isNotEmpty() && allCatalogContents.isEmpty()) _contents.value =
                        mutableListOf(MainRecommendHolder(recommendContents = allCatalogContents))
                    else if (catalogContent.isEmpty() && !allCatalogContents.isEmpty()) _contents.value =
                        mutableListOf(
                            MainPopularHolder(popularContents = catalogContent)
                        )
                    else _contents.value = mutableListOf(
                        MainPopularHolder(popularContents = catalogContent),
                        MainRecommendHolder(recommendContents = allCatalogContents)
                    )
                }


            }

        }
    }

    fun updateFavorite(id: Int, isLiked: Boolean) {
        viewModelScope.launch {
            updateFavorite(catalogRepository, id, isLiked)
        }
    }

    fun initContent() {
        viewModelScope.launch {
            catalogRepository.getAll("").collect {
                if (it.isEmpty()) {
                    catalogRepository.deleteAll()
                    catalogRepository.create(
                        listOf(
                            CatalogEntity(
                                category = "popular",
                                name = "Kamar sederhana",
                                shortDescription = "Kamar nyaman, dan aman untuk mengistirahatkan tubuh anda....",
                                description = "Kamar nyaman, dan aman untuk mengistirahatkan tubuh anda dari lelahnya menghadapi kerasnya kota. Kamar sederhana ini menyediakan beberapa fasilitas",
                                rate = 3.2,
                                ratedBy = 1254,
                                imageUrl = "https://ak-d.tripcdn.com/images/0225612000ddh5qriE0CE_Z_1080_808_R5_D.jpg",
                                isFavorite = false
                            ),
                            CatalogEntity(
                                category = "kamar-anak",
                                name = "Kamar bahagia",
                                shortDescription = "Kamar nyaman, dan aman untuk mengistirahatkan tubuh anda, bersama balita....",
                                description = "Kamar nyaman, dan aman untuk mengistirahatkan tubuh anda bersama sang balita dari lelahnya menghadapi kerasnya kota. Kamar sederhana ini menyediakan beberapa fasilitas ",
                                rate = 3.4,
                                ratedBy = 154,
                                imageUrl = "https://ak-d.tripcdn.com/images/0580y12000dyxrl2222BC_R_600_400_R5.webp",
                                isFavorite = false
                            ),
                            CatalogEntity(
                                category = "popular",
                                name = "Kamar Mewah",
                                shortDescription = "Kamar nyaman, dan aman untuk mengistirahatkan tubuh anda, bersama balita....",
                                description = "Kamar nyaman, dan aman untuk mengistirahatkan tubuh anda bersama sang balita dari lelahnya menghadapi kerasnya kota. Kamar sederhana ini menyediakan beberapa fasilitas ",
                                rate = 3.1,
                                ratedBy = 1545,
                                imageUrl = "https://ak-d.tripcdn.com/images/0585v12000dyxrtx01E81_R_600_400_R5.webp",
                                isFavorite = false
                            ),
                            CatalogEntity(
                                category = "kamar-mewah",
                                name = "Kamar Mewah",
                                shortDescription = "Nikmati kopi anda di balkon kamar anda....",
                                description = "Nikmati kopi anda di balkon kamar anda, dan rasakan sensasi indahnya menikmati sunset dan sunrise sembari menikmati keindahan kota",
                                rate = 3.8,
                                ratedBy = 1345,
                                imageUrl = "https://ak-d.tripcdn.com/images/0582d12000ddi33lv00A2_R_600_400_R5.webp",
                                isFavorite = false
                            ),
                            CatalogEntity(
                                category = "kamar-murah",
                                name = "Kamar Murah",
                                shortDescription = "Kecil, sederhana, namun tetap mempertahankan kebersihan dan kenyamanan.....",
                                description = "Kecil, sederhana, namun tetap mempertahankan kebersihan dan kenyamanan. Sehingga akan tetap membuat anda betah untuk menginap di kamar kami ini.",
                                rate = 4.0,
                                ratedBy = 1385,
                                imageUrl = "https://ak-d.tripcdn.com/images/0225212000ddh5qx7E332_R_600_400_R5.webp",
                                isFavorite = false
                            ),
                            CatalogEntity(
                                category = "popular",
                                name = "Kamar Keluarga",
                                shortDescription = "Anda memiliki banyak anggota keluarga ?, Tenang jangan khawatir....",
                                description = "Anda memiliki banyak anggota keluarga ?, Tenang jangan khawatir. Dengan adanya kamar ini, anda akan tetap merasakan nyamannya menginap sembari bercanda bersama seluruh keluarga anda",
                                rate = 4.0,
                                ratedBy = 1385,
                                imageUrl = "https://ak-d.tripcdn.com/images/0222i12000ddh5qrc815E_R_600_400_R5.webp",
                                isFavorite = false
                            ),
                            CatalogEntity(
                                category = "popular",
                                name = "Kamar Favorite",
                                shortDescription = "Ini adalah kamar kami yang paling laris. Tentu anda akan banyak mendapatkan ....",
                                description = "Ini adalah kamar kami yang paling laris. Tentu anda akan banyak mendapatkan fasilitas-fasilitas yang juga sangan menarik, banyak pelanggan kami yang sangat bahagia setelah menginap di kamar ini dengan pasangannya. Jadikan anda adalah pelanggan kami yang selanjutnya",
                                rate = 4.0,
                                ratedBy = 1385,
                                imageUrl = "https://ak-d.tripcdn.com/images/0227212000ddh5jwv9DBE_R_600_400_R5.webp",
                                isFavorite = false
                            )
                        )
                    )
                }
            }

        }
    }
}