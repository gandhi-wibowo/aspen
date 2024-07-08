package com.dizcoding.aspen.main.view.detail.content.facility

import com.dizcoding.adapterdelegate.bind
import com.dizcoding.adapterdelegate.itemDelegate
import com.dizcoding.aspen.R
import com.dizcoding.aspen.databinding.ActivityDetailCatalogContentFacilityItemBinding

fun facilityAdapter() =
    itemDelegate<Facility>(R.layout.activity_detail_catalog_content_facility_item)
        .bind {
            val binding = ActivityDetailCatalogContentFacilityItemBinding.bind(containerView)
            binding.textViewFacilityName.text = it.name
            binding.imageViewIcon.setImageResource(it.icon)
        }