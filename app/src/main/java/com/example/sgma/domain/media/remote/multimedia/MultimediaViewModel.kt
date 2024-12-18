package com.example.sgma.domain.media.remote.multimedia

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sgma.domain.media.Media
import com.example.sgma.domain.media.remote.multimedia.usecases.FindMediaUsecase
import com.example.sgma.domain.media.remote.multimedia.usecases.GetMultimediaUsecase
import com.example.sgma.domain.media.remote.multimedia.usecases.GetPopularMultimediaListUsecase
import com.example.sgma.presentation.ui.fakelist.getFakeMediaList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

class MultimediaViewModel(
    private val getMultimediaUsecase : GetMultimediaUsecase,
    private val getPopularMultimediaList: GetPopularMultimediaListUsecase,
    private val findMediaUsecase: FindMediaUsecase
) : ViewModel() {

    private val _multimedia : MutableLiveData<Multimedia> = MutableLiveData()
    val multimedia : LiveData<Multimedia> = _multimedia

    private val _mediaList : MutableLiveData<List<Media>> = MutableLiveData()
    val mediaList : LiveData<List<Media>> = _mediaList

    private var cahedMedia : MutableList<Media> = mutableListOf()
    val query = MutableStateFlow("")

    private var page : Int = 1

    init {
        getMediaList()
        _multimedia.value = Multimedia()

        CoroutineScope(Dispatchers.Main).launch {
            query.debounce(400).collect {
                findMultimediaByKeyword(it)
            }
        }
    }

    fun findMultimedia(id : Int) {
        viewModelScope.launch {
            _multimedia.value = getMultimediaUsecase(id)
        }
    }

    fun getMediaList() {
        viewModelScope.launch {
            getPopularMultimediaList(page).forEach { cahedMedia.add(it) }
            _mediaList.value = cahedMedia
        }
    }

    fun findMultimediaByKeyword(keyword : String) {
        if (keyword == "") {
            _mediaList.value = cahedMedia
        }
        else {
            viewModelScope.launch {
                _mediaList.value = findMediaUsecase(keyword)
            }
        }
    }
}