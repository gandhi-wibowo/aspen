package com.dizcoding.aspen.main.view.detail.content.detail

import com.dizcoding.adapterdelegate.bind
import com.dizcoding.adapterdelegate.itemDelegate
import com.dizcoding.aspen.R
import com.dizcoding.aspen.databinding.ActivityDetailCatalogContentItemBinding
import com.dizcoding.aspen.main.utility.extension.loadImage
import com.dizcoding.aspen.main.utility.extension.setDrawableRight


fun detailAdapter(isFavorite: (Detail) -> Unit) =
    itemDelegate<Detail>(R.layout.activity_detail_catalog_content_item)
        .bind {
            val binding = ActivityDetailCatalogContentItemBinding.bind(containerView)
            // image
            binding.imageViewCatalog.loadImage(it.catalogImage, R.dimen.rad_24)
            // like
            if (it.catalogIsLoved) binding.imageViewCatalogIsFavorite.loadImage(R.drawable.ic_heart_pink_filled_24)
            else binding.imageViewCatalogIsFavorite.loadImage(R.drawable.ic_heart_gray_outline_24)
            binding.textViewCatalogName.text = it.catalogName
            binding.textViewCatalogRate.text = it.catalogRate + it.catalogRatedBy
            binding.textViewCatalogDescription.text = it.catalogShortDescription
            var readMoreIsExpanded = false
            binding.textViewReadMore.setOnClickListener { _ ->
                readMoreIsExpanded = !readMoreIsExpanded
                if (readMoreIsExpanded) {
                    binding.textViewCatalogDescription.text = it.catalogDescription
                    binding.textViewReadMore.apply {
                        text = "Read Less"
                        setDrawableRight(R.drawable.ic_arrow_up_24)
                    }
                    // read less
                } else {
                    // read more
                    binding.textViewCatalogDescription.text = it.catalogShortDescription
                    binding.textViewReadMore.apply {
                        text = "Read More"
                        setDrawableRight(R.drawable.ic_arrow_down_blue_24)
                    }
                }
            }
            binding.imageViewCatalogIsFavorite.setOnClickListener { _ ->
                it.catalogIsLoved = !it.catalogIsLoved
                if (it.catalogIsLoved) binding.imageViewCatalogIsFavorite.loadImage(R.drawable.ic_heart_pink_filled_24)
                else binding.imageViewCatalogIsFavorite.loadImage(R.drawable.ic_heart_gray_outline_24)
                isFavorite.invoke(it)
            }
        }