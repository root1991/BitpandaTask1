package com.example.root.bitpandatesttask.wallets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.root.bitpandatesttask.model.MetalWallet
import com.example.root.bitpandatesttask.model.Wallet
import com.example.root.bitpandatesttask.repository.Repository
import com.example.root.bitpandatesttask.repository.Repository.FilterType
import com.example.root.bitpandatesttask.repository.Repository.SortType
import com.example.root.bitpandatesttask.wallets.WalletsViewModel.ViewState.Normal
import kotlinx.coroutines.launch

class WalletsViewModel(private val repository: Repository) : ViewModel() {

    private val state = MutableLiveData<ViewState>()
    fun state(): LiveData<ViewState> = state

    fun loadWallets() {
        viewModelScope.launch {
            handleWalletsResult(repository.getWallets())
        }
    }

    private fun handleWalletsResult(wallets: List<Wallet>) {
        viewModelScope.launch {
            state.postValue(
                Normal(wallets.map {
                    WalletListItem(
                        it.name,
                        repository.getWalletPicture(it),
                        it.balance.toString(),
                        repository.getDetailText(it),
                        if (it is MetalWallet)
                            repository.getMetalNameById(it.metalId) else null
                    )
                })
            )
        }
    }

    fun sortWalletList() {
        viewModelScope.launch {
            handleWalletsResult(repository.getWallets(SortType.Balance))
        }
    }

    fun filterCrypto() {
        viewModelScope.launch {
            handleWalletsResult(repository.getWallets(filterType = FilterType.Crypto))
        }
    }

    fun filterMetal() {
        viewModelScope.launch {
            handleWalletsResult(repository.getWallets(filterType = FilterType.Metal))
        }
    }

    fun filterFiat() {
        viewModelScope.launch {
            handleWalletsResult(repository.getWallets(filterType = FilterType.Fiat))
        }
    }

    sealed class ViewState {
        data class Normal(val contacts: List<WalletListItem>) : ViewState()
        object Error : ViewState()
    }

    data class WalletListItem(
        val walletName: String,
        val logo: String,
        val balance: String,
        val detailText: String,
        val metalName: String? = null
    )
}