package com.example.sgma.domain.media.local

import android.content.Context
import com.example.sgma.data.datasource.local.LocalDatasource
import com.example.sgma.data.datasource.local.LocalDatasourceImpl
import com.example.sgma.data.mapper.MediaDBModelMapper
import com.example.sgma.data.repository.LocalMediaRepositoryImpl
import com.example.sgma.domain.media.local.usecases.CheckMediaInDBUsecase
import com.example.sgma.domain.media.local.usecases.DeleteMediaUsecase
import com.example.sgma.domain.media.local.usecases.GetAllMediaUsecase
import com.example.sgma.domain.media.local.usecases.InsertMediaUsecase
import com.example.sgma.domain.media.local.usecases.SelectByTypeUsecase
import com.example.sgma.domain.media.local.usecases.UpdateStatusTypeUsecase
import com.example.sgma.domain.media.local.viemodel.LocalMediaViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalMediaModule {
    @Provides
    @Singleton
    fun provideLocalDatasource(context: Context) : LocalDatasource {
        return LocalDatasourceImpl(context)
    }

    @Provides
    @Singleton
    fun provideMediaRepository(localDatasource: LocalDatasource) : LocalMediaRepository {
        return LocalMediaRepositoryImpl(localDatasource, MediaDBModelMapper())
    }

    @Provides
    @Singleton
    fun provideMediaViewModel(repository: LocalMediaRepository) : LocalMediaViewModel {
        return LocalMediaViewModel(
            insertMediaUsecase = InsertMediaUsecase(repository),
            getAllMediaUsecase = GetAllMediaUsecase(repository),
            checkMediaInDBUsecase = CheckMediaInDBUsecase(repository),
            selectByTypeUsecase = SelectByTypeUsecase(repository),
            updateStatusTypeUsecase = UpdateStatusTypeUsecase(repository),
            deleteMediaUsecase = DeleteMediaUsecase(repository)
        )
    }
}