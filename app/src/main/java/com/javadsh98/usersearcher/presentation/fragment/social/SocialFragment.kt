package com.javadsh98.usersearcher.presentation.fragment.social

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.javadsh98.hilttest.presentation.fragment.search.UsersAdapter
import com.javadsh98.hilttest.presentation.model.UserItemModel
import com.javadsh98.hilttest.presentation.utils.viewBinding
import com.javadsh98.usersearcher.R
import com.javadsh98.usersearcher.databinding.FragmentSocialBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_social.*

@AndroidEntryPoint
class SocialFragment : Fragment(R.layout.fragment_social) {

    val navargs by navArgs<SocialFragmentArgs>()
    val viewBinding by viewBinding(FragmentSocialBinding::bind)
    val viewmodel by viewModels<SocialViewModel>()
    val adapter = UsersAdapter(::onItemClicked)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObserver()
        setupRecycler()
        viewmodel.getSocial(navargs.type, navargs.username)

    }

    private fun setupObserver() {

        viewmodel.socialUser.observe(viewLifecycleOwner){
            it?.let {
                adapter.submitList(it)
            }
        }
    }

    private fun setupRecycler() {
        with(viewBinding) {
            val recycler = rv_user_social
            recycler.setHasFixedSize(true)
            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = adapter
        }
    }

    fun onItemClicked(item: UserItemModel){
        val action = SocialFragmentDirections.actionSocialFragmentToDetailFragment(navargs.username)
        findNavController().navigate(action)
    }

}