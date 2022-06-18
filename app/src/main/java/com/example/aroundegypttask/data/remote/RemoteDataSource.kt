package com.example.aroundegypttask.data.remote

import com.example.aroundegypttask.data.model.experience.DetailsResponse
import com.example.aroundegypttask.data.model.experience.ExperienceResponse
import com.example.aroundegypttask.data.model.experience.LikeResponse
import io.reactivex.Single
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RemoteDataSource {
    @GET("experiences?filter[recommended]=true")
    fun getRecommendedExperience(): Single<Result<ExperienceResponse>>

    @GET("experiences")
    fun getRecentExperience(): Single<Result<ExperienceResponse>>

    @GET("experiences/{id}")
    fun getExperienceDetails(@Path("id") id: String): Single<Result<DetailsResponse>>

    @POST("experiences/{id}/like")
    fun postLike(@Path("id") id: String): Single<Result<LikeResponse>>
}