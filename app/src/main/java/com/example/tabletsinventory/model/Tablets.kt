package com.example.tabletsinventory.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "tablets_table")
data class Tablets(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val tagNum: String,
    val location: String,
    val custodian: String,
    val accessoriesSend: String,
    val accessoriesRec: String,
    val issueDate: String,
    val projectName: String
): Parcelable