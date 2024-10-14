package com.example.sgma.domain.media.viemodel

import android.util.Log
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.runtime.collection.mutableVectorOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sgma.data.entity.StatusType
import com.example.sgma.domain.Media
import com.example.sgma.domain.media.usecases.CheckMediaInDBUsecase
import com.example.sgma.domain.media.usecases.DeleteMediaUsecase
import com.example.sgma.domain.media.usecases.GetAllMediaUsecase
import com.example.sgma.domain.media.usecases.InsertMediaUsecase
import com.example.sgma.domain.media.usecases.SelectByTypeUsecase
import com.example.sgma.domain.media.usecases.UpdateStatusTypeUsecase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class LocalMediaViewModel(
    private val getAllMediaUsecase: GetAllMediaUsecase,
    private val insertMediaUsecase: InsertMediaUsecase,
    private val checkMediaInDBUsecase: CheckMediaInDBUsecase,
    private val updateStatusTypeUsecase: UpdateStatusTypeUsecase,
    private val selectByTypeUsecase: SelectByTypeUsecase,
    private val deleteMediaUsecase: DeleteMediaUsecase
) : ViewModel() {

    private val _localMedia : MutableLiveData<List<Media?>> = MutableLiveData()
    val localMedia : LiveData<List<Media?>> = _localMedia

    private val _inDB : MutableLiveData<Boolean> = MutableLiveData()
    val inDB : LiveData<Boolean> = _inDB

    init {
        getAllMedia()
        _inDB.value = false
    }

    fun getAllMedia() {
        CoroutineScope(Dispatchers.IO).launch {
            _localMedia.postValue(getAllMediaUsecase())
        }
    }

    fun insertMedia(media: Media) {
        CoroutineScope(Dispatchers.IO).launch {
            insertMediaUsecase(media)
        }
    }

    fun getMediaByStatusType(statusType: StatusType) {
        CoroutineScope(Dispatchers.IO).launch {
            _localMedia.postValue(selectByTypeUsecase(statusType))
        }
    }

    fun updateStatusType(statusType: StatusType, id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            updateStatusTypeUsecase(statusType, id)
        }
    }

    fun checkMediaInDB(id : Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val media = checkMediaInDBUsecase(id)
            _inDB.postValue(media?.id != -1)
        }
    }

    fun deleteMedia(media: Media) {
        CoroutineScope(Dispatchers.IO).launch {
            deleteMediaUsecase(media)
        }
    }
}