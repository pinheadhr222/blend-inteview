package com.interview.blend.module

import com.interview.blend.NavigatorImpl
import com.interview.ui.Navigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object AppModule {

    @Provides
    fun provideNavigator():Navigator = NavigatorImpl()
}