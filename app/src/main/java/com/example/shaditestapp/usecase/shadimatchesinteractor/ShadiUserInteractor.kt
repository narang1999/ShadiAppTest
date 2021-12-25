package com.example.shaditestapp.usecase.shadimatchesinteractor


import androidx.annotation.WorkerThread
import com.example.shaditestapp.model.ShadiUserMatchesInfo
import com.example.shaditestapp.model.Results
import com.example.shaditestapp.usecase.repository.ShadiUser
import com.example.shaditestapp.usecase.repository.ShadiUserDatabase
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class ShadiUserInteractor(val shadiUserDao: ShadiUserDatabase) {

    fun getAllUsersMatches(): Observable<List<ShadiUserMatchesInfo>> =
        shadiUserDao.wordDao()
            .getAlphabetizedWords()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    @WorkerThread
    fun insert(userMatches: List<Results>): Completable {
        val userData = mutableListOf<ShadiUser>()
        userMatches.forEach { users ->
            userData.add(
                ShadiUser(
                    users.name.first,
                    users.email,
                    users.dob.age,
                    users.picture.thumbnail
                )
            )
        }
        return shadiUserDao.wordDao().insert(userData)
    }

    fun insertStatus(id: Int, status: String): Completable =
        shadiUserDao.wordDao().insertStatus(id, status).subscribeOn(Schedulers.io())

}