package com.dizcoding.aspen.main.view.home.content.recommended

import androidx.recyclerview.widget.LinearLayoutManager
import com.dizcoding.adapterdelegate.DelegatesAdapter
import com.dizcoding.adapterdelegate.bind
import com.dizcoding.adapterdelegate.itemDelegate
import com.dizcoding.aspen.R
import com.dizcoding.aspen.databinding.ActivityMainCategoryHolderBinding
import com.dizcoding.aspen.main.view.home.content.MainContent

fun mainRecommendHolderAdapter(
    seeAllClicked: () -> Unit,
    itemClick: (MainContent) -> Unit,
    isLiked: (MainContent) -> Unit
) =
    itemDelegate<MainRecommendHolder>(R.layout.activity_main_category_holder)
        .bind {
            val binding = ActivityMainCategoryHolderBinding.bind(containerView)
            binding.textViewCategory.text = "Recommend"
            val adapter = DelegatesAdapter(
                mainRecommendAdapter(itemClick, isLiked)
            )
            binding.recyclerViewCatalogPopular.layoutManager = LinearLayoutManager(containerView.context)
            binding.recyclerViewCatalogPopular.adapter = adapter

            if (!it.recommendContents.isNullOrEmpty()) adapter.submitList(it.recommendContents!!)

            binding.textViewAll.setOnClickListener {
                seeAllClicked.invoke()
            }

        }