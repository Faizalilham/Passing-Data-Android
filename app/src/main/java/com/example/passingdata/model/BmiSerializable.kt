package com.example.passingdata.model

import java.io.Serializable

data class BmiSerializable(
    val age: Int,
    val height : Int,
    val weight : Int,
    val bmi : Int,
    val category : String
):Serializable