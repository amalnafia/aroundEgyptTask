package com.example.aroundegypttask.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aroundegypttask.domain.useCase.GetRecentExperienceUseCase
import com.example.aroundegypttask.domain.useCase.GetRecommendedExperienceUseCase

class HomeViewModelFactory(
    private val recentExperienceUseCase: GetRecentExperienceUseCase,
    private val recommendedExperienceUseCase: GetRecommendedExperienceUseCase
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java))
            return HomeViewModel(recentExperienceUseCase, recommendedExperienceUseCase) as T
        else
            throw IllegalArgumentException("Unknown ViewModel class")

    }
}

