package com.interview.blend.module

import com.interview.repo.SearchRepo
import com.interview.repo.SearchRepoImpl
import com.interview.repo.api.AuthorizationInterceptor
import com.interview.repo.api.SearchApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
object RepoModule {
    private const val BASE_URL = "https://api.imgur.com"
    private const val CLIENT_ID = "6685dd82a89f8ec"

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
    fun provideOKHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthorizationInterceptor(CLIENT_ID))
            .build()
    }

    @Provides
    fun provideRemoteApi(
        client: OkHttpClient
    ): SearchApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(SearchApi::class.java)
    }
}