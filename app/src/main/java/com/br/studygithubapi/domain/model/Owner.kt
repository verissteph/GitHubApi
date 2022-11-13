package com.br.studygithubapi.domain.model

import com.google.gson.annotations.SerializedName

data class Owner(
    @SerializedName("login")
    val login:String,
    @SerializedName("avatar_url")
    val photo: String
)
