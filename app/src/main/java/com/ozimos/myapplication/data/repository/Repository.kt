package com.ozimos.myapplication.data.repository

import com.ozimos.myapplication.data.remote.DataResponse
import com.ozimos.myapplication.data.repository.IRepository
import com.ozimos.myapplication.data.remote.DataApi
import javax.inject.Inject

class Repository @Inject constructor(private val api : DataApi) : IRepository {
    override suspend fun getListData(): Result<List<DataResponse>> {
       try {
           val response = api.getList()
           if (response.isSuccessful){
               val data = response.body()
               return Result.success(data ?: emptyList())
           } else {
               return Result.failure(Throwable("data empty"))
           }
       } catch (e : Exception){
           return Result.failure(e)
       }
    }

}