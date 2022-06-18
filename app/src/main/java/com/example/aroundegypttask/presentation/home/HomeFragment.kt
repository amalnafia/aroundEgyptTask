package com.example.aroundegypttask.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.example.aroundegypttask.R
import com.example.aroundegypttask.databinding.HomeFragmentBinding
import com.example.aroundegypttask.domain.model.Experience
import com.example.aroundegypttask.domain.useCase.GetRecentExperienceUseCaseProvider
import com.example.aroundegypttask.domain.useCase.GetRecommendedExperienceUseCaseProvider
import com.example.aroundegypttask.util.Resource


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!
    private val recommendedAdapter: HomeAdapter by lazy {
        HomeAdapter { onItemClick(it) }
    }
    private val recentAdapter: HomeAdapter by lazy {
        HomeAdapter { onItemClick(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initUI()
        observeUI()
        homeViewModel.getRecentExperience()
        homeViewModel.getRecommendedExperience()
    }

    private fun initViewModel() {
        val factory =
            HomeViewModelFactory(
                GetRecentExperienceUseCaseProvider.provide(requireContext()),
                GetRecommendedExperienceUseCaseProvider.provide(requireContext())
            )
        homeViewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)
    }

    private fun initUI() {
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.recommendedRv)
        binding.recommendedRv.adapter = recommendedAdapter
        binding.recentRv.adapter = recentAdapter

    }

    private fun observeUI() {
        homeViewModel.recentExperience.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    binding.progress.visibility = View.GONE
                    val data = it.data
                    recentAdapter.setAdapterModel(data)
                }
                is Resource.Error -> {
                    binding.progress.visibility = View.GONE
                    it.message?.let { message ->

                    }
                }
                is Resource.Loading -> {
                    binding.progress.visibility = View.VISIBLE
                }
            }

        }
        homeViewModel.recommendedExperience.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    binding.progress.visibility = View.GONE
                    val data = it.data
                    recommendedAdapter.setAdapterModel(data)
                }
                is Resource.Error -> {
                    binding.progress.visibility = View.GONE
                    it.message?.let { message ->

                    }
                }
                is Resource.Loading -> {
                    binding.progress.visibility = View.VISIBLE
                }
            }

        }
    }

    private fun onItemClick(data: Experience) {
        val bundle = bundleOf("id" to data.id)

        NavHostFragment.findNavController(this)
            .navigate(R.id.action_homeFragment_to_detailsFragment, bundle)
    }

}