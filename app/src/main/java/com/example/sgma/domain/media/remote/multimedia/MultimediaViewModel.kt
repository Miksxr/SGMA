package com.example.sgma.domain.media.remote.multimedia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sgma.domain.media.Media
import com.example.sgma.domain.media.remote.multimedia.usecases.GetMultimediaUsecase
import com.example.sgma.domain.media.remote.multimedia.usecases.GetPopularMultimediaListUsecase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MultimediaViewModel(
    private val getMultimediaUsecase : GetMultimediaUsecase,
    private val getPopularMultimediaList: GetPopularMultimediaListUsecase
) : ViewModel() {

    private val _multimedia : MutableLiveData<Multimedia> = MutableLiveData()
    val multimedia : LiveData<Multimedia> = _multimedia

    private val _mediaList : MutableLiveData<List<Media>> = MutableLiveData()
    val mediaList : LiveData<List<Media>> = _mediaList

    init {
        getMediaList()
        _multimedia.value = Multimedia()
    }

    fun findMultimedia(id : Int) {
        viewModelScope.launch {
            _multimedia.value = getMultimediaUsecase(id)
        }
    }

    fun getMediaList(page : Int = 1) {
        viewModelScope.launch {
            _mediaList.value = getPopularMultimediaList(page)
        }
    }
}