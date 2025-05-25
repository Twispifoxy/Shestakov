package com.example.shestakov

import com.google.gson.annotations.SerializedName


data class City (

  @SerializedName("name"       ) var name       : String?  = null,
  @SerializedName("latitude"   ) var latitude   : Double?  = null,
  @SerializedName("longitude"  ) var longitude  : Double?  = null,
  @SerializedName("country"    ) var country    : String?  = null,
  @SerializedName("population" ) var population : Int?     = null,
  @SerializedName("is_capital" ) var isCapital  : Boolean? = null

)