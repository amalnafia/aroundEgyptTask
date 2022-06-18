package com.example.aroundegypttask.data.model.experience

import com.google.gson.annotations.SerializedName


data class Reviews (

  @SerializedName("id"         ) var id         : String? = null,
  @SerializedName("experience" ) var experience : String? = null,
  @SerializedName("name"       ) var name       : String? = null,
  @SerializedName("rating"     ) var rating     : Int?    = null,
  @SerializedName("comment"    ) var comment    : String? = null,
  @SerializedName("created_at" ) var createdAt  : String? = null

)