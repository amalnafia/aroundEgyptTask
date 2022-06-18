package com.example.aroundegypttask.data

import android.content.Context
import com.example.aroundegypttask.data.local.ExperienceDatabase
import com.example.aroundegypttask.data.model.ExperienceRemoteMapper
import com.example.aroundegypttask.data.remote.RemoteDataSource

object HomeRepositoryImpProvider {
    private fun provideRemoteDateSource() =
        NetworkModule().provideApiService(RemoteDataSource::class.java)

    private fun provideLocalDateSource(context: Context) =
        ExperienceDatabase.getInstance(context)?.databaseDao()!!

    fun provide(context: Context): HomeRepositoryImp {
        return HomeRepositoryImp(
            remoteDataSource = provideRemoteDateSource(),
            localDataSource = provideLocalDateSource(context),
            experienceMapper = ExperienceRemoteMapper
        )
    }
}