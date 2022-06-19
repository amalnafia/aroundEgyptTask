package com.example.aroundegypttask.data

import com.example.aroundegypttask.data.local.DataBaseDao
import com.example.aroundegypttask.data.model.ExperienceRemoteMapper
import com.example.aroundegypttask.data.remote.RemoteDataSource
import com.example.aroundegypttask.domain.model.Experience
import com.example.aroundegypttask.domain.repo.HomeRepository
import io.reactivex.Single

class HomeRepositoryImp(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: DataBaseDao,
    private val experienceMapper: ExperienceRemoteMapper

) : HomeRepository {
    override fun getRecentExperience(): Single<List<Experience>> {
        return localDataSource.selectExperienceList()
            .flatMap {
                if (it.isEmpty())
                    return@flatMap Single.error(Exception("DB is Empty!"))
                else
                    return@flatMap Single.just(it)
            }.onErrorResumeNext {
                remoteDataSource.getRecentExperience()
                    .flatMap {
                        val result = it.response()?.body()?.data
                        val response = experienceMapper.map(result!!)
                        localDataSource.insertAll(response)
                        return@flatMap Single.just(response)
                    }
            }
    }

    override fun getRecommendedExperience(): Single<List<Experience>> {
        return localDataSource.selectRecommendedExperienceList()
            .flatMap {
                if (it.isEmpty())
                    return@flatMap Single.error(Exception("DB is Empty!"))
                else
                    return@flatMap Single.just(it)
            }
            .onErrorResumeNext {
                remoteDataSource.getRecommendedExperience()
                    .flatMap {
                        val result = it.response()?.body()?.data
                        val response = experienceMapper.map(result!!)
                        localDataSource.insertAll(response)
                        return@flatMap Single.just(response)
                    }
            }
    }

    override fun getExperienceDetails(id: String): Single<Experience> {
        return localDataSource.selectExperience(id)
            .onErrorResumeNext {
                remoteDataSource.getExperienceDetails(id)
                    .flatMap {
                        val result = it.response()?.body()?.data
                        val response = experienceMapper.map(result!!)
                        localDataSource.insertExperience(response)
                        return@flatMap Single.just(response)
                    }
            }
    }

    override fun postLike(id: String): Single<String> {
        return remoteDataSource.postLike(id)
            .map {
                val result = it.response()?.body()?.data
                localDataSource.updateLikeNo(id, result ?: "", 1)
                result ?: ""
            }
    }
}