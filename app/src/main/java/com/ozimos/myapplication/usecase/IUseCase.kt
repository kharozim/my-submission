package com.ozimos.myapplication.usecase

import com.ozimos.myapplication.data.remote.DataResponse

interface IUseCase {

    suspend fun getDataList() : Result<List<DataResponse>>
}