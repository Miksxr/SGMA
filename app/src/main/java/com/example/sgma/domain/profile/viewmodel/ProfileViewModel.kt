package com.example.sgma.domain.profile.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sgma.domain.comment.Comment
import com.example.sgma.domain.profile.Profile
import com.example.sgma.domain.profile.usecases.AddCommentUsecase
import com.example.sgma.domain.profile.usecases.AddFriendUsecase
import com.example.sgma.domain.profile.usecases.ChangeDescriptionUsecase
import com.example.sgma.domain.profile.usecases.ChangeImageUsecase
import com.example.sgma.domain.profile.usecases.ChangeNameUsecase
import com.example.sgma.domain.profile.usecases.DeleteCommentUsecase
import com.example.sgma.domain.profile.usecases.DeleteFriendUsecase
import com.example.sgma.domain.profile.usecases.GetAccountUsecase
import com.example.sgma.domain.profile.usecases.RegisterAccountUsecase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val commentAddUsecase: AddCommentUsecase,
    private val commentDeleteUsecase: DeleteCommentUsecase,
    private val friendDeleteUsecase: DeleteFriendUsecase,
    private val friendAddUsecase: AddFriendUsecase,
    private val changeImageUsecase: ChangeImageUsecase,
    private val changeNameUsecase: ChangeNameUsecase,
    private val changeDescriptionUsecase: ChangeDescriptionUsecase,
    private val getAccountUsecase: GetAccountUsecase,
    private val registerAccountUsecase: RegisterAccountUsecase
) : ViewModel() {

    private val _account : MutableLiveData<Profile> = MutableLiveData()
    val account : LiveData<Profile> = _account

    private val _lastActionResult : MutableLiveData<Boolean> = MutableLiveData()
    val lastActionResult : LiveData<Boolean> = _lastActionResult

    fun getAccountData(name : String) {
        viewModelScope.launch {
            _account.value = getAccountUsecase(name)
        }
    }

    fun updateAccountName(name: String, account : String) {
        viewModelScope.launch {
            _lastActionResult.value = changeNameUsecase(name, account)
        }
        val tempAcc = _account.value ?: null
        if (tempAcc != null) {
            tempAcc.name = name
            _account.value = tempAcc!!
        }
    }

    fun updateAccountImage(idImage: Int, account : String) {
        viewModelScope.launch {
            _lastActionResult.value = changeImageUsecase(idImage, account)
        }
        val tempAcc = _account.value ?: null
        if (tempAcc != null) {
            tempAcc.image = idImage
            _account.value = tempAcc!!
        }
    }

    fun updateAccountDescription(description: String, account : String) {
        viewModelScope.launch {
            _lastActionResult.value = changeDescriptionUsecase(description, account)
        }
        val tempAcc = _account.value ?: null
        if (tempAcc != null) {
            tempAcc.description = description
            _account.value = tempAcc!!
        }
    }

    fun addComment(comment: Comment, accName: String) {
        viewModelScope.launch {
            _lastActionResult.value = commentAddUsecase(comment, accName)
        }
        val tempAcc = _account.value ?: null
        if (tempAcc != null) {
            val list : MutableList<Comment> = tempAcc.comments.toMutableList()
            list.add(comment)
            tempAcc.comments = list
            _account.value = tempAcc!!
        }
    }

    fun deleteComment(comment: Comment, accName: String) {
        viewModelScope.launch {
            _lastActionResult.value = commentDeleteUsecase(comment, accName)
        }
        val tempAcc = _account.value ?: null
        if (tempAcc != null) {
            val list : MutableList<Comment> = tempAcc.comments.toMutableList()
            list.remove(comment)
            tempAcc.comments = list
            _account.value = tempAcc!!
        }
    }

    fun addFriend(friendAcc : String, accName: String) {
        viewModelScope.launch {
            _lastActionResult.value = friendAddUsecase(friendAcc, accName)
        }
        val tempAcc = _account.value ?: null
        if (tempAcc != null) {
            val list : MutableList<String> = tempAcc.friends.toMutableList()
            list.add(friendAcc)
            tempAcc.friends = list
            _account.value = tempAcc!!
        }
    }

    fun deleteFriend(friendAcc : String, accName: String) {
        viewModelScope.launch {
            _lastActionResult.value = friendDeleteUsecase(friendAcc, accName)
        }
        val tempAcc = _account.value ?: null
        if (tempAcc != null) {
            val list : MutableList<String> = tempAcc.friends.toMutableList()
            list.remove(friendAcc)
            tempAcc.friends = list
            _account.value = tempAcc!!
        }
    }

    fun registerAccount(account : Profile) {
        viewModelScope.launch {
            _lastActionResult.value = registerAccountUsecase(account)
        }
    }

}