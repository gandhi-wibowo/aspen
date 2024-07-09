package com.dizcoding.aspen.main.view.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.dizcoding.adapterdelegate.DelegatesAdapter
import com.dizcoding.aspen.R
import com.dizcoding.aspen.databinding.ActivityHomeBinding
import com.dizcoding.aspen.main.utility.noactionbarpadding.NoActionBarPadding
import com.dizcoding.aspen.main.utility.noactionbarpadding.NoActionBarPaddingImpl
import com.dizcoding.aspen.main.utility.viewbindinginflater.ViewBindingInflater
import com.dizcoding.aspen.main.utility.viewbindinginflater.ViewBindingInflaterImpl
import com.dizcoding.aspen.main.view.detail.DetailCatalogActivity
import com.dizcoding.aspen.main.view.favorite.FavoriteActivity
import com.dizcoding.aspen.main.view.home.content.popular.mainPopularHolderAdapter
import com.dizcoding.aspen.main.view.home.content.recommended.mainRecommendHolderAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity(),
    NoActionBarPadding by NoActionBarPaddingImpl(),
    ViewBindingInflater<ActivityHomeBinding> by ViewBindingInflaterImpl() {

    private val viewModel: HomeActivityViewModel by viewModel()

    private val adapter = DelegatesAdapter(
        mainPopularHolderAdapter({
        }, { itemClick ->
            startActivity(
                Intent(this, DetailCatalogActivity::class.java).putExtra(
                    "id",
                    itemClick.catalogId
                )
            )
        }, { onLikeClicked ->
            viewModel.updateFavorite(onLikeClicked.catalogId,onLikeClicked.isCatalogFavorite)
        }),
        mainRecommendHolderAdapter({
        }, { itemClick ->
            startActivity(
                Intent(this, DetailCatalogActivity::class.java).putExtra(
                    "id",
                    itemClick.catalogId
                )
            )
        }, { onLikeClicked ->
            viewModel.updateFavorite(onLikeClicked.catalogId,onLikeClicked.isCatalogFavorite)
        }),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViewBindingInflater(this) { ActivityHomeBinding.inflate(layoutInflater) }
        handleNoActionBarPaddingBy(requireNotNull(binding.root))

        viewModel.searchBy("")
        viewModel.initContent()

        binding.constraintLayoutBottomMenu.setOnClickListener { }
        binding.imageViewFavorite.setOnClickListener {
            startActivity(Intent(this, FavoriteActivity::class.java))
        }

        binding.recyclerViewCatalog.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewCatalog.adapter = adapter

        binding.searchViewCatalog.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.searchBy(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.searchBy(newText)
               return true
            }
        })
        binding.imageViewButtonFilterFavorite.setOnClickListener {
            if (viewModel.getLatestStatusFilterByFavorite()){
                binding.imageViewButtonFilterFavorite.setImageResource(R.drawable.ic_heart_gray_outline_24)
            }else{
                binding.imageViewButtonFilterFavorite.setImageResource(R.drawable.ic_heart_pink_filled_24)
            }
            viewModel.filterByFavorite()
        }

        viewModel.contents.observe(this){
            adapter.submitList(it)
        }

    }
}