package com.example.sgma.domain.comment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sgma.domain.comment.usecases.AddCommentUsecase
import com.example.sgma.domain.comment.usecases.GetCommentsUsecase
import com.example.sgma.domain.comment.Comment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CommentViewModel(
    private val getCommentsUsecase: GetCommentsUsecase,
    private val addCommentsUsecase: AddCommentUsecase
) : ViewModel() {

    private val _comments : MutableStateFlow<List<Comment>> = MutableStateFlow(emptyList())
    val comments : StateFlow<List<Comment>> = _comments


    fun getComments(filmId : Int) {
        viewModelScope.launch {
            _comments.emit(getCommentsUsecase(filmId))
        }
    }

    fun addComments(filmId: Int, comment: Comment) {
        viewModelScope.launch {
            addCommentsUsecase(filmId, comment)
        }
//        val list = _comments.value?.toMutableList()
//        list?.add(comment)
//        _comments.value = list ?: emptyList()
    }

}