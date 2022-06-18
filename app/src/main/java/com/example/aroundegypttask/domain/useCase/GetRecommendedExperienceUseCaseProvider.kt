package com.example.aroundegypttask.domain.useCase

import android.content.Context
import com.example.aroundegypttask.data.HomeRepositoryImpProvider

object GetRecommendedExperienceUseCaseProvider {
    private fun provideExperienceRepo(context: Context) =
        HomeRepositoryImpProvider.provide(context)

    fun provide(context: Context): GetRecommendedExperienceUseCase {
        return GetRecommendedExperienceUseCase(
            homeRepository = provideExperienceRepo(context)
        )
    }
}