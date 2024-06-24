package com.ozimos.myapplication.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozimos.myapplication.data.remote.DataResponse
import com.ozimos.myapplication.usecase.IUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataViewModel @Inject constructor(private val useCase: IUseCase) : ViewModel() {

    private val _datas = MutableLiveData<StateUtil<List<DataResponse>>>()
    val datas: LiveData<StateUtil<List<DataResponse>>>
        get() = _datas

    fun getDataList() {
        viewModelScope.launch {
            try {
                _datas.postValue(StateUtil.Loading())
                val response = useCase.getDataList()
                response.fold(
                    onSuccess = {
                        _datas.postValue(StateUtil.Success(it))
                    },
                    onFailure = {
                        _datas.postValue(StateUtil.Error(it.message.toString()))
                    }

                )
            } catch (e: Exception) {
                _datas.postValue(StateUtil.Error(e.message.toString()))
            }
        }
    }
}