package com.ozimos.myapplication.usecase

import com.ozimos.myapplication.data.remote.DataResponse
import com.ozimos.myapplication.data.repository.IRepository
import com.ozimos.myapplication.usecase.IUseCase
import javax.inject.Inject

class UseCase @Inject constructor(private val repo: IRepository) : IUseCase {
    override suspend fun getDataList(): Result<List<DataResponse>> {
        return repo.getListData()
    }
}