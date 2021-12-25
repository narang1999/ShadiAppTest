package com.example.shaditestapp.usecase.repository

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shadi_user")
class ShadiUser(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "age") val age: Int,
    @ColumnInfo(name = "picture") val picture: String,
    @ColumnInfo(name = "accept") var accept: String = "default",
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)

