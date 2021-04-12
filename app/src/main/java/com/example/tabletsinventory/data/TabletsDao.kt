package com.example.tabletsinventory.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tabletsinventory.model.Tablets

@Dao
interface TabletsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addTablet(tablets: Tablets)

    @Update
    suspend fun updateTablet(tablets: Tablets)

    @Query("SELECT * FROM tablets_table ORDER BY id ASC")
    fun readAllData():LiveData<List<Tablets>>
}