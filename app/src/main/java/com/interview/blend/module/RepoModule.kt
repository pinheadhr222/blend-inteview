package com.interview.blend.module

import com.interview.repo.SearchRepo
import com.interview.repo.SearchRepoImpl
import com.interview.repo.api.SearchApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

///TODO
//1. Need to create an okhttp client with bearer

@Module
@InstallIn(ViewModelComponent::class)
object RepoModule {
    private const val BASE_URL = "BASE-URI"
    private const val API_KEY = "CLIENT-ID"

    @Provides
    @ViewModelScoped
    fun provideRepository(
        api: SearchApi
    ): SearchRepo {
        return SearchRepoImpl(
            api = api
        )
    }

    @Provides
    fun provideRemoteApi(): SearchApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SearchApi::class.java)
    }
}