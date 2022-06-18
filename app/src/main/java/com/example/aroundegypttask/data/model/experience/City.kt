package com.example.aroundegypttask.data.model.experience

import com.google.gson.annotations.SerializedName


data class City (

  @SerializedName("id"       ) var id      : Int?    = null,
  @SerializedName("name"     ) var name    : String? = null,
  @SerializedName("disable"  ) var disable : String? = null,
  @SerializedName("top_pick" ) var topPick : Int?    = null

)