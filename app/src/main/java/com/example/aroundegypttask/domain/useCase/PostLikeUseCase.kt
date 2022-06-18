package com.example.aroundegypttask.domain.useCase

import com.example.aroundegypttask.domain.repo.HomeRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PostLikeUseCase(
    private val homeRepository: HomeRepository
) {
    fun postLike(id: String): Single<String> {
        return homeRepository.postLike(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}