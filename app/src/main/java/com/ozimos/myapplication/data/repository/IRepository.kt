package com.ozimos.myapplication.data.repository

import com.ozimos.myapplication.data.remote.DataResponse

interface IRepository {

    suspend fun getListData() : Result<List<DataResponse>>
}