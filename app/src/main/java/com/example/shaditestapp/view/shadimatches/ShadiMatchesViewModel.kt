package com.example.shaditestapp.view.shadimatches

import android.annotation.SuppressLint
import androidx.lifecycle.*
import com.example.shaditestapp.di.launch
import com.example.shaditestapp.model.ShadiUserMatchesInfo
import com.example.shaditestapp.model.Results
import com.example.shaditestapp.usecase.repository.Repository
import com.example.shaditestapp.usecase.shadimatchesinteractor.ShadiUserInteractor
import com.example.shaditestapp.utils.exception.NoConnectionException
import io.reactivex.android.schedulers.AndroidSchedulers


class ShadiMatchesViewModel(
    private val repository: Repository,
    private val shadiUserInteractor: ShadiUserInteractor
) : ViewModel() {
    val error: LiveData<Throwable> get() = _error
    private var _error: MutableLiveData<Throwable> = MutableLiveData<Throwable>()
    val onApiError: LiveData<Unit> get() = _onApiError
    private var _onApiError: MutableLiveData<Unit> = MutableLiveData<Unit>()
    val allMatchesUsers: LiveData<List<ShadiUserMatchesInfo>> get() = _allMatchesUsers

    private var _allMatchesUsers: MutableLiveData<List<ShadiUserMatchesInfo>> =
        MutableLiveData<List<ShadiUserMatchesInfo>>()

    init {
        launch {
            shadiUserInteractor.getAllUsersMatches()
                .subscribe({ _allMatchesUsers.postValue(it) }, this::onError)
        }
        getMatchedUsers()
    }


    fun onStatusBtnClicked(id: Int, status: String) {
        launch {
            shadiUserInteractor.insertStatus(id, status)
                .subscribe({ }, this::onError)
        }
    }

    fun getMatchedUsers() {
        launch {
            repository.getShadiMatchesUsers()
                .map { it.results }
                .subscribe({ insertMatches(it) }, this::onError)
        }
    }


    @SuppressLint("CheckResult")
    private fun insertMatches(result: List<Results>) {
        shadiUserInteractor.insert(result)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({}, this::onError)
    }

    private fun onError(throwable: Throwable) {
        if (throwable is NoConnectionException)
            _onApiError.postValue(Unit)
        else _error.postValue(throwable)

    }

}
