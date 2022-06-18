package com.example.aroundegypttask.data.model.experience

import com.google.gson.annotations.SerializedName


data class Period (

  @SerializedName("id"         ) var id        : String? = null,
  @SerializedName("value"      ) var value     : String? = null,
  @SerializedName("created_at" ) var createdAt : String? = null,
  @SerializedName("updated_at" ) var updatedAt : String? = null

)