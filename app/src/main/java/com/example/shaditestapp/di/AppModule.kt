package com.example.shaditestapp.di


import com.example.shaditestapp.usecase.repository.*
import com.example.shaditestapp.usecase.repository.interceptor.ConnectivityInterceptor
import com.example.shaditestapp.usecase.shadimatchesinteractor.ShadiUserInteractor
import com.example.shaditestapp.view.shadimatches.ShadiMatchesViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val app_module = module {
    viewModel { ShadiMatchesViewModel(get(),get()) }
}
val single_module = module {
    single { retrofit.create(ShadiApi::class.java) }
    single { ShadiUserInteractor(ShadiUserDatabase.getDatabase(get())) }
    single { Repository(get()) }
}
val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

val client = OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(
    ConnectivityInterceptor()
).build()

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://randomuser.me/")
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .client(client)
    .build()


val disposables = CompositeDisposable()

fun launch(job: () -> Disposable) {
    disposables.add(job())
}