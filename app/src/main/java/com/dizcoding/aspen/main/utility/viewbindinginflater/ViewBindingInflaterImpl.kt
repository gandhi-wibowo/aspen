package com.dizcoding.aspen.main.utility.viewbindinginflater

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

@Suppress("UNCHECKED_CAST")
class ViewBindingInflaterImpl<VB : ViewBinding> : ViewBindingInflater<VB> {
    private var _binding: ViewBinding? = null

    override val binding: VB
        get() = _binding as VB

    override fun setViewBindingInflater(
        activity: AppCompatActivity,
        layout: (LayoutInflater) -> VB
    ) {
        _binding = layout.invoke(activity.layoutInflater)
        activity.setContentView(requireNotNull(binding).root)
    }

}