package com.dizcoding.aspen.main.view.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dizcoding.adapterdelegate.DelegatesAdapter
import com.dizcoding.aspen.databinding.ActivityDetailCatalogBinding
import com.dizcoding.aspen.main.utility.noactionbarpadding.NoActionBarPadding
import com.dizcoding.aspen.main.utility.noactionbarpadding.NoActionBarPaddingImpl
import com.dizcoding.aspen.main.utility.viewbindinginflater.ViewBindingInflater
import com.dizcoding.aspen.main.utility.viewbindinginflater.ViewBindingInflaterImpl
import com.dizcoding.aspen.main.view.detail.content.detail.detailAdapter
import com.dizcoding.aspen.main.view.detail.content.facility.facilityHolderAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailCatalogActivity : AppCompatActivity(),
    NoActionBarPadding by NoActionBarPaddingImpl(),
    ViewBindingInflater<ActivityDetailCatalogBinding> by ViewBindingInflaterImpl() {

    private val viewModel: DetailCatalogActivityViewModel by viewModel()
    private val adapter = DelegatesAdapter(
        detailAdapter { item ->
            viewModel.updateFavorite(item.catalogId,item.catalogIsLoved)
        },
        facilityHolderAdapter()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViewBindingInflater(this) { ActivityDetailCatalogBinding.inflate(layoutInflater) }
        handleNoActionBarPaddingBy(requireNotNull(binding.root))

        binding.recyclerViewCatalogDetail.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewCatalogDetail.adapter = adapter

        viewModel.contents().observe(this) {
            adapter.submitList(it)
        }

        val catalogId = intent.getIntExtra("id",0)
        viewModel.fetchContents(catalogId)


        binding.imageViewArrowBack.setOnClickListener {
            finish()
        }
    }
}