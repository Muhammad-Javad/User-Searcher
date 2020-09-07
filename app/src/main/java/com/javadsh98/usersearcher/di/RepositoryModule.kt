package com.javadsh98.usersearcher.di

import com.javadsh98.usersearcher.data.repository.RepositoryImpl
import com.javadsh98.usersearcher.domain.repository.IRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule{

    @Binds
    abstract fun getRepository(repositoryImpl: RepositoryImpl): IRepository

}