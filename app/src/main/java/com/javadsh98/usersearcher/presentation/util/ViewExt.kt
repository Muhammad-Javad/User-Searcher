package com.javadsh98.usersearcher.presentation.util

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@ExperimentalCoroutinesApi
fun SearchView.searching(): StateFlow<String> {
    val query = MutableStateFlow("")
    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            newText?.let {
                query.value = it
                return true
            }
            return false
        }

    })
    return query
}

fun TextView.setTextAndVisible(text: String?){
    if (text.isNullOrEmpty()){
        visibility = View.GONE
    }else{
        visibility = View.VISIBLE
        text?.let {
            this.text = it
        }
    }
}