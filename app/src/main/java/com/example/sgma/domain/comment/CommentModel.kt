package com.example.sgma.domain.comment

import com.example.sgma.data.datasource.remote.comment.RemoteCommentDatasource
import com.example.sgma.data.datasource.remote.comment.RemoteCommentDatasourceImpl
import com.example.sgma.data.mapper.accounts.CommentMapper
import com.example.sgma.data.repository.CommentRepositoryImpl
import com.example.sgma.domain.comment.usecases.AddCommentUsecase
import com.example.sgma.domain.comment.usecases.GetCommentsUsecase
import com.example.sgma.domain.comment.viewmodel.CommentViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CommentModel {
    @Provides
    @Singleton
    fun provideRemoteCommentDatasource() : RemoteCommentDatasource {
        return RemoteCommentDatasourceImpl()
    }

    @Provides
    @Singleton
    fun provideCommentRepository(remoteCommentDatasource: RemoteCommentDatasource) : CommentRepository {
        return CommentRepositoryImpl(remoteCommentDatasource, CommentMapper())
    }

    @Provides
    @Singleton
    fun provideCommentsViewModel(repository: CommentRepository) : CommentViewModel {
        return CommentViewModel(
            addCommentsUsecase = AddCommentUsecase(repository),
            getCommentsUsecase = GetCommentsUsecase(repository)
        )
    }
}