package com.example.aroundegypttask.domain.useCase

import android.content.Context
import com.example.aroundegypttask.data.HomeRepositoryImpProvider

object GetRecentExperienceUseCaseProvider {
    private fun provideExperienceRepo(context: Context) =
        HomeRepositoryImpProvider.provide(context)

    fun provide(context: Context): GetRecentExperienceUseCase {
        return GetRecentExperienceUseCase(
            homeRepository = provideExperienceRepo(context)
        )
    }
}