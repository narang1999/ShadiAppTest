package com.example.shaditestapp.usecase.repository

import androidx.room.*
import com.example.shaditestapp.model.ShadiUserMatchesInfo
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface ShadiUserDao {

    @Query("SELECT * FROM shadi_user")
     fun getAlphabetizedWords(): Observable<List<ShadiUserMatchesInfo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insert(name: MutableList<ShadiUser>):Completable

    @Query("UPDATE shadi_user SET accept = :status WHERE id =:id")
    fun insertStatus(id:Int,status:String):Completable

}