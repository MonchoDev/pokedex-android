package com.moncho.thepokedex.service

import com.google.gson.annotations.SerializedName

data class ApiResult(
    @SerializedName("count") var count : Int? = null,
    @SerializedName("next") var next : String? = null,
    @SerializedName("previous") var previous : String? = null,
    @SerializedName("results") var results  : ArrayList<Results> = arrayListOf()
)

data class Results(
    @SerializedName("name" ) var name : String? = null,
    @SerializedName("url"  ) var url  : String? = null
)