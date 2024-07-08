package com.dizcoding.aspen.main.view.favorite

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.dizcoding.adapterdelegate.DelegatesAdapter
import com.dizcoding.aspen.databinding.ActivityFavoriteBinding
import com.dizcoding.aspen.main.utility.noactionbarpadding.NoActionBarPadding
import com.dizcoding.aspen.main.utility.noactionbarpadding.NoActionBarPaddingImpl
import com.dizcoding.aspen.main.utility.viewbindinginflater.ViewBindingInflater
import com.dizcoding.aspen.main.utility.viewbindinginflater.ViewBindingInflaterImpl
import com.dizcoding.aspen.main.view.detail.DetailCatalogActivity
import com.dizcoding.aspen.main.view.home.HomeActivity
import com.dizcoding.aspen.main.view.home.content.recommended.mainRecommendAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class FavoriteActivity : AppCompatActivity(),
    NoActionBarPadding by NoActionBarPaddingImpl(),
    ViewBindingInflater<ActivityFavoriteBinding> by ViewBindingInflaterImpl() {

    private val viewModel: FavoriteActivityViewModel by viewModel()

    private val adapter = DelegatesAdapter<Any>(
        mainRecommendAdapter({ itemClick ->
            startActivity(
                Intent(this, DetailCatalogActivity::class.java).putExtra(
                    "id",
                    itemClick.catalogId
                )
            )
        }, { onLikeClicked ->
            viewModel.updateFavorite(onLikeClicked.catalogId, onLikeClicked.isCatalogFavorite)
        })
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViewBindingInflater(this) { ActivityFavoriteBinding.inflate(layoutInflater) }
        handleNoActionBarPaddingBy(requireNotNull(binding.root))

        viewModel.searchBy("")

        binding.constraintLayoutBottomMenu.setOnClickListener { }
        binding.imageViewHome.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
        binding.imageViewArrowBack.setOnClickListener { finish() }

        binding.recyclerViewCatalogDetail.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewCatalogDetail.adapter = adapter

        viewModel.contents.observe(this) {
            adapter.submitList(it)
        }

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
    }
}