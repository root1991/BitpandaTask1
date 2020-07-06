package com.example.root.bitpandatesttask

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.root.bitpandatesttask.repository.Repository
import com.example.root.bitpandatesttask.wallets.WalletsViewModel

class ViewModelFactory(
    private val repository: Repository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        WalletsViewModel::class.java -> WalletsViewModel(repository)
        else -> error("Unknown view model class $modelClass")
    } as T
}