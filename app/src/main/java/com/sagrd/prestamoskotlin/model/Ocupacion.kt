package com.sagrd.prestamoskotlin.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "Ocupaciones")
@Parcelize
data class Ocupacion(
    @PrimaryKey(autoGenerate = true)
    val ocupacionId: Int,
    val descripcion: String,
    val Ingresos: Float
): Parcelable
