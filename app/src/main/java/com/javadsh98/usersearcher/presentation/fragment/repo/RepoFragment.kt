package com.javadsh98.usersearcher.presentation.fragment.repo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.javadsh98.hilttest.presentation.fragment.repo.ReposAdapter
import com.javadsh98.hilttest.presentation.utils.viewBinding
import com.javadsh98.usersearcher.R
import com.javadsh98.usersearcher.databinding.FragmentRepoBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class RepoFragment : Fragment(R.layout.fragment_repo){

    val viewBinding by viewBinding(FragmentRepoBinding::bind)
    val viewmodel by viewModels<ReposViewModel>()
    val args by navArgs<RepoFragmentArgs>()
    val adapter = ReposAdapter{

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecycler()
        setupObserver()
        viewmodel.getRepos(args.username)
    }

    private fun setupObserver() {
        viewmodel.repos.observe(viewLifecycleOwner){
            it?.let {
                adapter.submitList(it)
            }
        }
        Timber.i("$viewmodel")
    }

    private fun setupRecycler() {
        with(viewBinding) {
            val recycler = rvRepoList
            recycler.setHasFixedSize(true)
            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = adapter
        }
    }

}