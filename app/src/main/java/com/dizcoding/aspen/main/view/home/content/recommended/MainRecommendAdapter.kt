package com.dizcoding.aspen.main.view.home.content.recommended

import com.dizcoding.adapterdelegate.bind
import com.dizcoding.adapterdelegate.click
import com.dizcoding.adapterdelegate.itemDelegate
import com.dizcoding.aspen.R
import com.dizcoding.aspen.databinding.ActivityMainCategoryRecommendItemBinding
import com.dizcoding.aspen.main.view.home.content.MainContent
import com.dizcoding.aspen.main.utility.extension.loadImage

fun mainRecommendAdapter(itemClick: (MainContent) -> Unit, isLiked: (MainContent) -> Unit) =
    itemDelegate<MainContent>(R.layout.activity_main_category_recommend_item)
        .click(itemClick)
        .bind {
            val binding = ActivityMainCategoryRecommendItemBinding.bind(containerView)
            binding.textViewCatalogName.text = it.catalogName
            binding.textViewCatalogRate.text = it.catalogRate

            binding.imageViewCatalog.loadImage(it.catalogImage, R.dimen.rad_24)

            if (it.isCatalogFavorite) {
                binding.imageViewCatalogLike.setImageResource(R.drawable.ic_heart_pink_filled_24)
            } else {
                binding.imageViewCatalogLike.setImageResource(R.drawable.ic_heart_gray_outline_24)
            }


            binding.imageViewCatalogLike.setOnClickListener { c ->
                it.isCatalogFavorite = !it.isCatalogFavorite
                if (it.isCatalogFavorite) {
                    binding.imageViewCatalogLike.setImageResource(R.drawable.ic_heart_pink_filled_24)
                } else {
                    binding.imageViewCatalogLike.setImageResource(R.drawable.ic_heart_gray_outline_24)
                }
                isLiked.invoke(it)
            }
        }