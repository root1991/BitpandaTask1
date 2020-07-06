package com.example.root.bitpandatesttask

import com.example.root.bitpandatesttask.model.DummyWebService
import com.example.root.bitpandatesttask.repository.Repository
import kotlinx.coroutines.Dispatchers

object ServiceLocator {

    private val dummyWebService: DummyWebService = DummyWebService()

    private val repository: Repository
        get() = Repository(dummyWebService, Dispatchers.IO)

    val viewModelFactory by lazy {
        ViewModelFactory(repository)
    }

}