package com.interview.repo.api

import com.interview.repo.data.Result
import com.interview.repo.data.SearchResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("/3/gallery/search")
    fun search(@Query("q") query: String): Call<Result<List<SearchResult>>>
}