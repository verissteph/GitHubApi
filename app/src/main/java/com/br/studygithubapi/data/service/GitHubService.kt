package com.br.studygithubapi.data.service

import com.br.studygithubapi.domain.model.Repositories
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubService {
    @GET("search/repositories?q=language:kotlin&sort=stars")
    fun getList(@Query("page") page: Int): Call<Repositories>
}