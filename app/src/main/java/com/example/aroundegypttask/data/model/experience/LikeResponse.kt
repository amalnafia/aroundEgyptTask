package com.example.aroundegypttask.data.model.experience

import com.google.gson.annotations.SerializedName


data class LikeResponse(
    @SerializedName("meta") var meta: Meta? = Meta(),
    @SerializedName("data") var data: String,
)