package com.budi.myapplication

import androidx.room.*

@Entity
data class Sound(
        @PrimaryKey val uid: Int,
        @ColumnInfo(name = "bgm") var bgm: Int,
        @ColumnInfo(name = "effect") var effect: Int
)