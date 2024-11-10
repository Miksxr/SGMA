package com.example.sgma.domain.media.remote.multimedia.usecases

import com.example.sgma.domain.media.Media
import com.example.sgma.domain.media.remote.multimedia.RemoteMultimediaRepository
import javax.inject.Inject

class GetPopularMultimediaListUsecase @Inject constructor(
    private val repository: RemoteMultimediaRepository
) {
    suspend operator fun invoke(page : Int = 1) : List<Media> {
        return repository.getPopularMultimediaList(page)
    }
}