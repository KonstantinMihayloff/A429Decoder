package com.example.a429decoder.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.a429decoder.Label

/*@Dao
interface LabelDao {
    // Get all labels from table "label"
    @Query("SELECT * FROM label")
    fun getAllLabels(): LiveData<List<Label>>

    // Get a particular label
    @Query("SELECT * from label WHERE id = :id")
    fun getLabelById(id: Int) : LiveData<Label>
}*/