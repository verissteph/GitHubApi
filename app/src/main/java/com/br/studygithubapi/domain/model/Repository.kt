package com.br.studygithubapi.domain.model

import com.google.gson.annotations.SerializedName

data class Repository(
    val totalPage:Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("forks_count")
    val forksCount: Int,
    @SerializedName("stargazers_count")
    val stargazersCount:Int,
    @SerializedName("owner")
    val owner:Owner
)
