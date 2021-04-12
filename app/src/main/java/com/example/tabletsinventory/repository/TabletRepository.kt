package com.example.tabletsinventory.repository

import androidx.lifecycle.LiveData
import com.example.tabletsinventory.model.Tablets
import com.example.tabletsinventory.data.TabletsDao

class TabletRepository(private val tabletsDao: TabletsDao) {

    val readAllData: LiveData<List<Tablets>> = tabletsDao.readAllData()

    fun addTablet(tablets: Tablets) {
        tabletsDao.addTablet(tablets)
    }

    suspend fun updateTablet(tablets: Tablets){
        tabletsDao.updateTablet(tablets)
    }
}