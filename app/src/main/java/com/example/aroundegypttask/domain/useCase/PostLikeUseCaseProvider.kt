package com.example.aroundegypttask.domain.useCase

import android.content.Context
import com.example.aroundegypttask.data.HomeRepositoryImpProvider

object PostLikeUseCaseProvider {
    private fun provideExperienceRepo(context: Context) =
        HomeRepositoryImpProvider.provide(context)

    fun provide(context: Context): PostLikeUseCase {
        return PostLikeUseCase(
            homeRepository = provideExperienceRepo(context)
        )
    }
}