package com.juvcarl.batch.mcs.characterlist.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.juvcarl.batch.mcs.characterlist.repository.model.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class Repository @Inject constructor(val api: ApiCall){

    fun getCharacters(q: String): LiveData<Resource<Result>> {

        val data = MutableLiveData<Resource<Result>>()

        api.getCharacters(q).enqueue(object : Callback<Result> {
            override fun onFailure(call: Call<Result>?, t: Throwable?) {
                val exception = Exception(t)
                data.value = Resource.error(exception)
            }

            override fun onResponse(call: Call<Result>?, response: Response<Result>?) {
                data.value = Resource.success(response?.body())
            }

        })
        return data
    }

}