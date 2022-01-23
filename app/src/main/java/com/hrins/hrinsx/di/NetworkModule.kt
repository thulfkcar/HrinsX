package com.hrins.hrinsx.di

import com.hrins.hrinsx.App
import com.hrins.hrinsx.network.NetworkMapper
import com.hrins.hrinsx.network.api.HttpHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Singleton
    @Provides
    fun provideHttpHandler(
        app: App,
    ): HttpHandler {
        return HttpHandler(app)
    }

    @Singleton
    @Provides
    fun provideNetworkMapper(): NetworkMapper {
        return NetworkMapper()
    }


}