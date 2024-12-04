package com.example.sgma.domain.media.remote.multimedia

import com.example.sgma.data.datasource.remote.multimedia.RemoteDatasourceMultimedia
import com.example.sgma.data.datasource.remote.multimedia.RemoteDatasourceMultimediaImpl
import com.example.sgma.data.mapper.MediaDtoModelMapper
import com.example.sgma.data.mapper.multimedia.MultimediaDtoModelMapper
import com.example.sgma.data.repository.RemoteMultimediaRepositoryImpl
import com.example.sgma.domain.media.remote.multimedia.usecases.FindMediaUsecase
import com.example.sgma.domain.media.remote.multimedia.usecases.GetMultimediaUsecase
import com.example.sgma.domain.media.remote.multimedia.usecases.GetPopularMultimediaListUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MultimediaModule {

    @Provides
    @Singleton
    fun provideRemoteDatasourceMultimedia() : RemoteDatasourceMultimedia {
        return RemoteDatasourceMultimediaImpl()
    }

    @Provides
    @Singleton
    fun provideMultimediaRepository(rm : RemoteDatasourceMultimedia) : RemoteMultimediaRepository {
        return RemoteMultimediaRepositoryImpl(
            remoteDatasourceMultimedia = rm,
            mediaDtoModelMapper = MediaDtoModelMapper(),
            multimedaMapper = MultimediaDtoModelMapper()
        )
    }

    @Provides
    @Singleton
    fun provideMultimediaViewModel(repository: RemoteMultimediaRepository) : MultimediaViewModel {
        return MultimediaViewModel(
            getMultimediaUsecase = GetMultimediaUsecase(repository),
            getPopularMultimediaList = GetPopularMultimediaListUsecase(repository),
            findMediaUsecase = FindMediaUsecase(repository)
        )
    }
}