package com.javadsh98.usersearcher.presentation.fragment.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.api.load
import coil.transform.RoundedCornersTransformation
import com.javadsh98.hilttest.data.remote.model.UserDetailResponse
import com.javadsh98.hilttest.presentation.utils.NumberFormatter
import com.javadsh98.hilttest.presentation.utils.viewBinding
import com.javadsh98.usersearcher.R
import com.javadsh98.usersearcher.databinding.FragmentDetailBinding
import com.javadsh98.usersearcher.presentation.util.setTextAndVisible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_detail.*

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    val navArgs by navArgs<DetailFragmentArgs>()
    val viewmodel by viewModels<DetailViewModel>()
    val viewBinding by viewBinding(FragmentDetailBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        requestDetail(navArgs.username)
        setupObserver()
        setupListener()
    }

    private fun setupListener() {
        with(viewBinding){
            btn_repo.setOnClickListener {
                goRepos()
            }
            btnFollowers.setOnClickListener {
                goSocial(1)
            }
            btnFollowing.setOnClickListener {
                goSocial(0)
            }
        }
    }

    private fun goRepos() {
        val action = DetailFragmentDirections.actionDetailFragmentToRepoFragment(navArgs.username)
        findNavController().navigate(action)
    }

    private fun goSocial(type: Int) {
        val action = DetailFragmentDirections.actionDetailFragmentToSocialFragment(navArgs.username, type)
        findNavController().navigate(action)
    }

    private fun requestDetail(username: String) {
        viewmodel.getUser(username)
    }

    private fun setupObserver() {
        viewmodel.userDetail.observe(viewLifecycleOwner) {
            setupDetail(it)
        }
    }

    private fun setupDetail(model: UserDetailResponse) {
        with(viewBinding){
            ivUserPic.load(model.avatarUrl){
                crossfade(true)
                transformations(RoundedCornersTransformation(20f))
            }
            tvName.setTextAndVisible(model.name)
            tvUserName.setTextAndVisible(model.login)
            tvBio.setTextAndVisible(model.bio)
            tvCompany.setTextAndVisible(model.company)
            tvLocation.setTextAndVisible(model.location)
            tvBlog.setTextAndVisible(model.blog)
            tvFollowersFollowing.setTextAndVisible(getFollowersFollowing(model.followers, model.following))
            tvRepoCount.setTextAndVisible(model.publicRepos.toString())
            tvFollowersCount.setTextAndVisible(NumberFormatter.formatWithSuffix(model.followers))
            tvFollowingCount.setTextAndVisible(NumberFormatter.formatWithSuffix(model.following))
        }
    }

    private fun getFollowersFollowing(followers: Int, following: Int): String {

        val followersStr = NumberFormatter.formatWithSuffix(followers)
        val followingStr = NumberFormatter.formatWithSuffix(following)
        return "$followersStr followers ▪ $followingStr following"
    }



}