package com.example.sgma.domain.profile.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sgma.domain.profile.Comment
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

    init {
        //check internet connection!!!
    }

    fun getAccountData(name : String) {
        CoroutineScope(Dispatchers.IO).launch {
            _account.postValue(getAccountUsecase(name))
        }
    }

    fun updateAccountName(name: String, account : String) {
        CoroutineScope(Dispatchers.IO).launch {
            _lastActionResult.postValue(changeNameUsecase(name, account))
        }
    }

    fun updateAccountImage(idImage: Int, account : String) {
        CoroutineScope(Dispatchers.IO).launch {
            _lastActionResult.postValue(changeImageUsecase(idImage, account))
        }
    }

    fun updateAccountDescription(description: String, account : String) {
        CoroutineScope(Dispatchers.IO).launch {
            _lastActionResult.postValue(changeDescriptionUsecase(description, account))
        }
    }

    fun addComment(comment: Comment, accName: String) {
        CoroutineScope(Dispatchers.IO).launch {
            _lastActionResult.postValue(commentAddUsecase(comment, accName))
        }
    }

    fun deleteComment(comment: Comment, accName: String) {
        CoroutineScope(Dispatchers.IO).launch {
            _lastActionResult.postValue(commentDeleteUsecase(comment, accName))
        }
    }

    fun addFriend(friendAcc : String, accName: String) {
        CoroutineScope(Dispatchers.IO).launch {
            _lastActionResult.postValue(friendAddUsecase(friendAcc, accName))
        }
    }

    fun deleteFriend(friendAcc : String, accName: String) {
        CoroutineScope(Dispatchers.IO).launch {
            _lastActionResult.postValue(friendDeleteUsecase(friendAcc, accName))
        }
    }

    fun registerAccount(account : Profile) {
        CoroutineScope(Dispatchers.IO).launch {
            _lastActionResult.postValue(registerAccountUsecase(account))
        }
    }

}