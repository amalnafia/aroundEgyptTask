package com.example.aroundegypttask.domain.useCase

import android.content.Context
import com.example.aroundegypttask.data.HomeRepositoryImpProvider

object GetExperienceDetailsUseCaseProvider {
    private fun provideExperienceRepo(context: Context) =
        HomeRepositoryImpProvider.provide(context)

    fun provide(context: Context): GetExperienceDetailsUseCase {
        return GetExperienceDetailsUseCase(
            homeRepository = provideExperienceRepo(context)
        )
    }
}