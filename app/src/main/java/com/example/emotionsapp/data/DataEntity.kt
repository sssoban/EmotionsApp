package com.example.emotionsapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data_table")
data class DataEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "emotion_type") val emotionType: String,
    @ColumnInfo(name = "emotion_desc") val emotionDescription: String
)
