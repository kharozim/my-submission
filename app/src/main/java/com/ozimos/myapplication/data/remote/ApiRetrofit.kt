package com.ozimos.myapplication.data.remote

import com.ozimos.myapplication.data.repository.IRepository
import com.ozimos.myapplication.usecase.IUseCase
import com.ozimos.myapplication.data.repository.Repository
import com.ozimos.myapplication.usecase.UseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiRetrofit {

    @Singleton
    @Provides
    fun getRetrofit(): Retrofit {
        val baseUrl = "https://jsonplaceholder.typicode.com/"
        val converterFactory = GsonConverterFactory.create()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converterFactory)
            .build()

        return retrofit
    }

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): DataApi =
        retrofit.create(DataApi::class.java)

    @Singleton
    @Provides
    fun provideRepo(api : DataApi) : IRepository = Repository(api)

    @Singleton
    @Provides
    fun provideUseCase(repo : IRepository) : IUseCase = UseCase(repo)
}