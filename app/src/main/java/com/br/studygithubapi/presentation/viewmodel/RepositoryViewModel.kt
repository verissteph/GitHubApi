package com.br.studygithubapi.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.studygithubapi.data.service.InicializerApi
import com.br.studygithubapi.domain.model.Repositories
import com.br.studygithubapi.domain.model.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryViewModel : ViewModel() {
    private val users by lazy { InicializerApi.init() }
    val liveData: MutableLiveData<List<Repository>> = MutableLiveData()

    fun getRepository(page: Int) {
        users.getList(page).enqueue(object : Callback<Repositories> {
            override fun onResponse(call: Call<Repositories>, response: Response<Repositories>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        liveData.postValue(it.items)
                    }
                }
            }

            override fun onFailure(call: Call<Repositories>, t: Throwable) {
                Log.d("Error", t.message.toString())
            }
        })
    }
}
