package com.example.aroundegypttask.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.aroundegypttask.R
import com.example.aroundegypttask.databinding.DetailsFragmentBinding
import com.example.aroundegypttask.domain.useCase.GetExperienceDetailsUseCaseProvider
import com.example.aroundegypttask.domain.useCase.PostLikeUseCaseProvider
import com.example.aroundegypttask.util.Resource

class DetailsFragment : Fragment() {

    private lateinit var experienceId: String
    private lateinit var detailsViewModel: DetailsViewModel
    private var _binding: DetailsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        experienceId = arguments?.getString("id") ?: ""
        initViewModel()
        getExperienceDetails()
        observeUI()
        handleLikeClick()
    }

    private fun handleLikeClick() {
        binding.favouriteIv.setOnClickListener {
            if (detailsViewModel.detailsData?.isLiked == 0) {
                detailsViewModel.postLike(experienceId)
                binding.favouriteIv.setImageDrawable(requireContext().getDrawable(R.drawable.ic_favourite))
            }
        }
    }

    private fun observeUI() {
        detailsObserve()
        postLikeObserve()
    }

    private fun postLikeObserve() {
        detailsViewModel.postLike.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    val data = it.data
                    binding.favouriteCountTv.text = data ?: ""
                }
                is Resource.Error -> {
                    it.message?.let { message ->
                        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun detailsObserve() {
        detailsViewModel.experienceDetails.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    binding.progress.visibility = View.GONE
                    val data = it.data
                    detailsViewModel.detailsData = data
                    Glide.with(requireContext()).load(data?.coverPhoto).thumbnail(0.1f)
                        .into(binding.placeIv)
                    binding.viewTv.text = data?.viewsNo.toString()
                    binding.nameTv.text = data?.title
                    binding.locationTv.text = data?.address
                    binding.descriptionValueTv.text = data?.detailedDescription
                    binding.favouriteCountTv.text = data?.likesNo.toString()
                    if (data?.isLiked == 1) {
                        binding.favouriteIv.setImageDrawable(requireContext().getDrawable(R.drawable.ic_favourite))
                    } else
                        binding.favouriteIv.setImageDrawable(requireContext().getDrawable(R.drawable.ic_favourite_border))

                }
                is Resource.Error -> {
                    binding.progress.visibility = View.GONE
                    it.message?.let { message ->
                        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
                    }
                }
                is Resource.Loading -> {
                    binding.progress.visibility = View.VISIBLE
                }
            }
        }
    }


    private fun getExperienceDetails() {
        detailsViewModel.getRecentExperience(experienceId)
    }

    private fun initViewModel() {
        val factory =
            DetailsViewModelFactory(
                GetExperienceDetailsUseCaseProvider.provide(requireContext()),
                PostLikeUseCaseProvider.provide(requireContext())
            )
        detailsViewModel = ViewModelProvider(this, factory).get(DetailsViewModel::class.java)
    }
}