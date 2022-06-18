package com.example.aroundegypttask.presentation.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aroundegypttask.domain.model.Experience
import com.example.aroundegypttask.domain.useCase.GetExperienceDetailsUseCase
import com.example.aroundegypttask.domain.useCase.PostLikeUseCase
import com.example.aroundegypttask.util.Resource
import io.reactivex.disposables.CompositeDisposable

class DetailsViewModel(
    private val experienceDetailsUseCase: GetExperienceDetailsUseCase,
    private val postLikeUseCase: PostLikeUseCase
) :
    ViewModel() {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val experienceDetails: MutableLiveData<Resource<Experience>> = MutableLiveData()

    fun getRecentExperience(id: String) {
        experienceDetails.postValue(Resource.Loading())
        val disposable = experienceDetailsUseCase.getExperienceDetails(id).doOnSuccess {
            experienceDetails.postValue(Resource.Success(it))
        }.doOnError {
            experienceDetails.postValue(Resource.Error("Error", null))
        }.subscribe()
        compositeDisposable.add(disposable)
    }

    val postLike: MutableLiveData<Resource<String>> = MutableLiveData()

    fun postLike(id: String) {
        val disposable = postLikeUseCase.postLike(id).doOnSuccess {
            postLike.postValue(Resource.Success(it))
        }.doOnError {
            postLike.postValue(Resource.Error("Error", null))
        }.subscribe()
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    var detailsData: Experience? = null
}