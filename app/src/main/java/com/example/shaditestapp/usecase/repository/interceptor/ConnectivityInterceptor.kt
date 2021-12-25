package com.example.shaditestapp.usecase.repository.interceptor

import com.example.shaditestapp.utils.exception.NoConnectionException
import okhttp3.Interceptor
import okhttp3.Response
import java.net.ConnectException
import java.net.UnknownHostException

class ConnectivityInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            return chain.proceed(chain.request())
        } catch (exception: Exception) {
            throw if (exception is ConnectException ||
                exception is UnknownHostException
            ) NoConnectionException() else exception
        }
    }
}
