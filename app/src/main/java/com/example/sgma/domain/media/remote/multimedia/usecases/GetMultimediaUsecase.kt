package com.example.sgma.domain.media.remote.multimedia.usecases

import com.example.sgma.domain.media.remote.multimedia.Multimedia
import com.example.sgma.domain.media.remote.multimedia.RemoteMultimediaRepository
import javax.inject.Inject

class GetMultimediaUsecase @Inject constructor(
    private val repository: RemoteMultimediaRepository
) {
    suspend operator fun invoke(id : Int) : Multimedia {
        return repository.getMultimedia(id)
    }
}