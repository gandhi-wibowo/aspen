package com.dizcoding.aspen.main.view.home.content.popular

import androidx.recyclerview.widget.LinearLayoutManager
import com.dizcoding.adapterdelegate.DelegatesAdapter
import com.dizcoding.adapterdelegate.bind
import com.dizcoding.adapterdelegate.itemDelegate
import com.dizcoding.aspen.R
import com.dizcoding.aspen.databinding.ActivityMainCategoryHolderBinding
import com.dizcoding.aspen.main.view.home.content.MainContent

fun mainPopularHolderAdapter(
    seeAllClicked: () -> Unit,
    itemClick: (MainContent) -> Unit,
    isLiked: (MainContent) -> Unit
) =
    itemDelegate<MainPopularHolder>(R.layout.activity_main_category_holder)
        .bind {
            val binding = ActivityMainCategoryHolderBinding.bind(containerView)
            binding.textViewCategory.text = "Popular"
            val adapter = DelegatesAdapter(
                mainPopularAdapter(itemClick, isLiked)
            )
            binding.recyclerViewCatalogPopular.layoutManager =
                LinearLayoutManager(containerView.context, LinearLayoutManager.HORIZONTAL, false)
            binding.recyclerViewCatalogPopular.adapter = adapter

            if (!it.popularContents.isNullOrEmpty()) adapter.submitList(it.popularContents!!)

            binding.textViewAll.setOnClickListener {
                seeAllClicked.invoke()
            }

        }