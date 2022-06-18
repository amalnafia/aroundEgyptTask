package com.example.aroundegypttask.domain.useCase

import com.example.aroundegypttask.domain.model.Experience
import com.example.aroundegypttask.domain.repo.HomeRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GetExperienceDetailsUseCase(
    private val homeRepository: HomeRepository
) {
    fun getExperienceDetails(id: String): Single<Experience> {
        return homeRepository.getExperienceDetails(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}