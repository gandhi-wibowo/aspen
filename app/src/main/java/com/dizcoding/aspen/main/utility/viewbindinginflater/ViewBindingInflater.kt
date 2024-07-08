package com.dizcoding.aspen.main.utility.viewbindinginflater

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

interface ViewBindingInflater<VB : ViewBinding> {
    val binding: VB
    fun setViewBindingInflater(activity: AppCompatActivity, layout: (LayoutInflater) -> VB)
}