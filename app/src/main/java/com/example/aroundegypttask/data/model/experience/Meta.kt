package com.example.aroundegypttask.data.model.experience

import com.google.gson.annotations.SerializedName

data class Meta(

    @SerializedName("code") var code: Int? = null,
    @SerializedName("errors") var errors: ArrayList<String> = arrayListOf()

)