package com.example.sgma.domain.media.remote.multimedia.usecases

import com.example.sgma.domain.media.Media
import com.example.sgma.domain.media.remote.multimedia.RemoteMultimediaRepository
import javax.inject.Inject

class FindMediaUsecase @Inject constructor(
    private val repository: RemoteMultimediaRepository
) {
    suspend operator fun invoke(keyword : String) : List<Media> {
        return repository.findMedia(keyword)
    }
}