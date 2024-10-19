package com.example.sgma.domain.profile

import com.example.sgma.data.datasource.remote.accounts.RemoteAccountDatasource
import com.example.sgma.data.datasource.remote.accounts.RemoteAccountDatasourceImpl
import com.example.sgma.data.mapper.accounts.ProfileMapper
import com.example.sgma.data.repository.ProfileRepositoryImpl
import com.example.sgma.domain.media.usecases.DeleteMediaUsecase
import com.example.sgma.domain.profile.usecases.AddCommentUsecase
import com.example.sgma.domain.profile.usecases.AddFriendUsecase
import com.example.sgma.domain.profile.usecases.ChangeDescriptionUsecase
import com.example.sgma.domain.profile.usecases.ChangeImageUsecase
import com.example.sgma.domain.profile.usecases.ChangeNameUsecase
import com.example.sgma.domain.profile.usecases.DeleteCommentUsecase
import com.example.sgma.domain.profile.usecases.DeleteFriendUsecase
import com.example.sgma.domain.profile.usecases.GetAccountUsecase
import com.example.sgma.domain.profile.viewmodel.ProfileViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProfileModule {
    @Provides
    @Singleton
    fun provideRemoteAccountsDatasource() : RemoteAccountDatasource {
        return RemoteAccountDatasourceImpl()
    }

    @Provides
    @Singleton
    fun provideProfileRepository(remoteAccountDatasource: RemoteAccountDatasource)
    : ProfileRepository {
        return ProfileRepositoryImpl(remoteAccountDatasource, ProfileMapper())
    }

    @Provides
    @Singleton
    fun provideMediaViewModel(repository: ProfileRepository) : ProfileViewModel {
        return ProfileViewModel(
            commentAddUsecase = AddCommentUsecase(repository),
            commentDeleteUsecase = DeleteCommentUsecase(repository),
            friendDeleteUsecase = DeleteFriendUsecase(repository),
            friendAddFriendUsecase = AddFriendUsecase(repository),
            changeImageUsecase = ChangeImageUsecase(repository),
            changeNameUsecase = ChangeNameUsecase(repository),
            changeDescriptionUsecase = ChangeDescriptionUsecase(repository),
            getAccountUsecase = GetAccountUsecase(repository)
        )
    }
}