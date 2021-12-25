package com.example.shaditestapp.usecase.repository

import com.example.shaditestapp.model.DataResponse
import com.example.shaditestapp.model.Results
import io.reactivex.Maybe
import io.reactivex.schedulers.Schedulers


class Repository(
    private val smartApi: ShadiApi,
) : ShadiApi {
    override fun getShadiMatchesUsers(results:Int): Maybe<DataResponse<List<Results>>> =
        smartApi.getShadiMatchesUsers()
            .subscribeOn(Schedulers.io())

}