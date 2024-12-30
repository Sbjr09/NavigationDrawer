package com.sbjr.navigationdrawer

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_matches")
data class SavedData(
    @PrimaryKey val id: String,
    val name: String
)
