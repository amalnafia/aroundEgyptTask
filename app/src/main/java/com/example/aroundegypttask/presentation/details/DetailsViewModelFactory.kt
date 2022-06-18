package com.example.aroundegypttask.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aroundegypttask.domain.useCase.GetExperienceDetailsUseCase
import com.example.aroundegypttask.domain.useCase.PostLikeUseCase

class DetailsViewModelFactory(
    private val experienceDetailsUseCase: GetExperienceDetailsUseCase,
    private val postLikeUseCase: PostLikeUseCase
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java))
            return DetailsViewModel(experienceDetailsUseCase, postLikeUseCase) as T
        else
            throw IllegalArgumentException("Unknown ViewModel class")

    }
}

