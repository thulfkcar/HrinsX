package com.hrins.hrinsx.di

import com.hrins.hrinsx.App
import com.hrins.hrinsx.repositories.LaunchesRepo
import com.hrins.hrinsx.network.api.HttpHandler
import com.hrins.hrinsx.repositories.CompanyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideLaunchesRepo(httpHandler: HttpHandler, app: App): LaunchesRepo {
        return LaunchesRepo(httpHandler = httpHandler, app =app)
    }
    @Singleton
    @Provides
    fun provideCompanyRepository(httpHandler: HttpHandler, app: App): CompanyRepository {
        return CompanyRepository(httpHandler = httpHandler, app =app)
    }
}