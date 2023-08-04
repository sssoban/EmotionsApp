package com.example.emotionsapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DataDao {
    @Insert
    fun insertData(data: DataEntity)

    @Query("SELECT * FROM data_table")
    fun getAllData(): List<DataEntity>
}
