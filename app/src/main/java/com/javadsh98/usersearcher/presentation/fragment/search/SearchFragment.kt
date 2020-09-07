package com.javadsh98.usersearcher.presentation.fragment.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.javadsh98.hilttest.presentation.fragment.search.UsersAdapter
import com.javadsh98.hilttest.presentation.utils.viewBinding
import com.javadsh98.usersearcher.R
import com.javadsh98.usersearcher.databinding.FragmentSearchBinding
import com.javadsh98.usersearcher.presentation.util.searching
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    val viewbinding: FragmentSearchBinding by viewBinding(FragmentSearchBinding::bind)
    val viewModel: SearchViewModel by viewModels()
    val adapter = UsersAdapter{
        val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment(it.username)
        findNavController().navigate(action)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecycler()
        setupObserver()
        setupSearchview()

    }

    private fun setupSearchview() {
        with(viewbinding) {
            viewModel.searchUser(searchView.searching())
        }
    }

    private fun setupObserver() {
        viewModel.userSearched.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
            }
        }
        viewModel.message.observe(viewLifecycleOwner) {
            it?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupRecycler() {
        with(viewbinding) {
            rvUserSearch.setHasFixedSize(true)
            rvUserSearch.layoutManager = LinearLayoutManager(requireContext())
            rvUserSearch.adapter = adapter
        }
    }

}