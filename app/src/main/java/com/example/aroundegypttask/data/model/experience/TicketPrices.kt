package com.example.aroundegypttask.data.model.experience

import com.google.gson.annotations.SerializedName


data class TicketPrices (

  @SerializedName("type"  ) var type  : String? = null,
  @SerializedName("price" ) var price : Int?    = null

)