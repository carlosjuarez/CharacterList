package com.juvcarl.batch.mcs.characterlist.repository

import com.juvcarl.batch.mcs.characterlist.repository.model.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCall {
    @GET(".")
    fun getCharacters(@Query("q") q: String, @Query("format") format: String = "json") : Call<Result>
}