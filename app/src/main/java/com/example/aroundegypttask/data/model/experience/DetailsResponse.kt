package com.example.aroundegypttask.data.model.experience

import com.google.gson.annotations.SerializedName


data class DetailsResponse(
    @SerializedName("meta") var meta: Meta? = Meta(),
    @SerializedName("data") var data: ExperienceRemote,
)