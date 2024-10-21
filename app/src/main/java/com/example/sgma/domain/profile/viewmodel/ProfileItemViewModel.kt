package com.example.sgma.domain.profile.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sgma.domain.profile.Profile
import com.example.sgma.domain.profile.usecases.GetAccountListByNameUsecase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileItemViewModel(
    private val getAccountListUsecase: GetAccountListByNameUsecase
) : ViewModel() {

    private val _accounts : MutableLiveData<List<Profile>> = MutableLiveData()
    val accounts : LiveData<List<Profile>> = _accounts

    fun findAccounts(name: String) {
        CoroutineScope(Dispatchers.IO).launch {
            _accounts.postValue(getAccountListUsecase(name))
        }
    }
}