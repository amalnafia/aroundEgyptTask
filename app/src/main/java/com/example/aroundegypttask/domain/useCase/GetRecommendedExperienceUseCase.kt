package com.example.aroundegypttask.domain.useCase

import com.example.aroundegypttask.domain.model.Experience
import com.example.aroundegypttask.domain.repo.HomeRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GetRecommendedExperienceUseCase(
    private val homeRepository: HomeRepository
) {
    fun getExperience(): Single<List<Experience>> {
        return homeRepository.getRecommendedExperience()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}