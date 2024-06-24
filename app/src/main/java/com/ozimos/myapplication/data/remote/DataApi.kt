package com.ozimos.myapplication.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface DataApi {

    @GET("posts")
    suspend fun getList() : Response<List<DataResponse>>
}