package com.javadsh98.hilttest.presentation.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun Fragment.setTitle(title: String){
    val activity = requireActivity() as AppCompatActivity
    activity.supportActionBar?.let {
        it.title = title
    }
}