package com.dizcoding.aspen.main.view.detail.content.facility

import androidx.recyclerview.widget.GridLayoutManager
import com.dizcoding.adapterdelegate.DelegatesAdapter
import com.dizcoding.adapterdelegate.bind
import com.dizcoding.adapterdelegate.itemDelegate
import com.dizcoding.aspen.R
import com.dizcoding.aspen.databinding.ActivityDetailCatalogContentFacilityHolderBinding

fun facilityHolderAdapter() =
    itemDelegate<FacilityHolder>(R.layout.activity_detail_catalog_content_facility_holder)
        .bind {
            val binding = ActivityDetailCatalogContentFacilityHolderBinding.bind(containerView)
            binding.textViewFacility.text = "Facilities"
            binding.recyclerViewCatalogFacility.layoutManager =
                GridLayoutManager(containerView.context, 4)

            val adapter = DelegatesAdapter(
                facilityAdapter()
            )
            binding.recyclerViewCatalogFacility.adapter = adapter
            adapter.submitList(it.facilities)
        }