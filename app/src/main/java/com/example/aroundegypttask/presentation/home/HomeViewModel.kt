package com.example.aroundegypttask.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aroundegypttask.domain.useCase.GetRecentExperienceUseCase
import com.example.aroundegypttask.domain.model.Experience
import com.example.aroundegypttask.domain.useCase.GetRecommendedExperienceUseCase
import com.example.aroundegypttask.util.Resource
import io.reactivex.disposables.CompositeDisposable

class HomeViewModel(private val recentExperienceUseCase: GetRecentExperienceUseCase,private val recommendedExperienceUseCase: GetRecommendedExperienceUseCase) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val recentExperience: MutableLiveData<Resource<List<Experience>>> = MutableLiveData()
    val recommendedExperience: MutableLiveData<Resource<List<Experience>>> = MutableLiveData()

    fun getRecentExperience() {
        recentExperience.postValue(Resource.Loading())
        val disposable = recentExperienceUseCase.getExperience().doOnSuccess {
            recentExperience.postValue(Resource.Success(it))
        }.doOnError {
            recentExperience.postValue(Resource.Error(it.message!!, null))
        }.subscribe()
        compositeDisposable.add(disposable)
    }
    fun getRecommendedExperience() {
        recommendedExperience.postValue(Resource.Loading())
        val disposable = recommendedExperienceUseCase.getExperience().doOnSuccess {
            recommendedExperience.postValue(Resource.Success(it))
        }.doOnError {
            recommendedExperience.postValue(Resource.Error(it.message!!, null))
        }.subscribe()
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}