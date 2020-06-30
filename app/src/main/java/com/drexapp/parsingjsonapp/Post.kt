package com.drexapp.parsingjsonapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Post (
    val imageUser: String,
    val username: String,
    val imagePost: String,
    val like: Int
) : Parcelable