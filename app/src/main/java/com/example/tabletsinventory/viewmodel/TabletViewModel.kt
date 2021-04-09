package com.example.tabletsinventory.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.tabletsinventory.data.TabletDatabase
import com.example.tabletsinventory.model.Tablets
import com.example.tabletsinventory.repository.TabletRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TabletViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<Tablets>>
    private val repository: TabletRepository

    init {
        val tabletsDao = TabletDatabase.getDatabase(application).tabletsDao()
        repository = TabletRepository(tabletsDao)
        readAllData = repository.readAllData
    }

    fun addTablet(tablets: Tablets) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTablet(tablets)
        }
    }
}