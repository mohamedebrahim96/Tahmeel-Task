package com.tahmeel.task.di


import com.tahmeel.task.network.TahmeelClient
import com.tahmeel.task.persistence.TahmeelDao
import com.tahmeel.task.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideMainRepository(
        tahmeelClient: TahmeelClient,
        tahmeelDao: TahmeelDao
    ): MainRepository {
        return MainRepository(tahmeelClient, tahmeelDao)
    }

}
