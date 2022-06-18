package com.example.aroundegypttask.domain.repo

import com.example.aroundegypttask.domain.model.Experience
import io.reactivex.Single

interface HomeRepository {
    fun getRecentExperience(): Single<List<Experience>>
    fun getRecommendedExperience(): Single<List<Experience>>
    fun getExperienceDetails(id: String): Single<Experience>
    fun postLike(id: String): Single<String>
}