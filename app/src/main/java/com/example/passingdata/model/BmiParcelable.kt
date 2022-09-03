package com.example.passingdata.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BmiParcelable (
    val age: Int,
    val height : Int,
    val weight : Int,
    val bmi : Int,
    val category : String
):Parcelable