package com.example.root.bitpandatesttask.wallets

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.root.bitpandatesttask.R
import com.example.root.bitpandatesttask.ServiceLocator
import com.example.root.bitpandatesttask.details.DETAIL_TEXT_EXTRA
import com.example.root.bitpandatesttask.details.DetailsActivity
import com.example.root.bitpandatesttask.wallets.WalletsViewModel.ViewState.Normal
import com.example.root.bitpandatesttask.wallets.WalletsViewModel.WalletListItem
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item_wallet.view.*

class WalletsActivity : AppCompatActivity() {

    private val viewModel: WalletsViewModel by viewModels {
        ServiceLocator.viewModelFactory
    }
    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        GroupAdapter<GroupieViewHolder>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.loadWallets()
        walletsRecyclerView.adapter = adapter

        viewModel.state().observe(this, Observer { state ->
            when (state) {
                is Normal -> showList(state.contacts)
            }
        })
        adapter.setOnItemClickListener { item, _ ->
            showDetails((item as WalletListViewItem).wallet.detailText)
        }
    }

    private fun showDetails(detailText: String) {
        val intent = Intent(this, DetailsActivity::class.java).apply {
            putExtra(DETAIL_TEXT_EXTRA, detailText)
        }
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.wallets_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        with(viewModel) {
            when (item.itemId) {
                R.id.sort -> sortWalletList()
                R.id.crypto -> viewModel.filterCrypto()
                R.id.fiat -> viewModel.filterFiat()
                R.id.metal -> viewModel.filterMetal()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showList(wallets: List<WalletListItem>) {
        adapter.clear()
        adapter.addAll(wallets.map(WalletsActivity::WalletListViewItem))
    }

    private class WalletListViewItem(val wallet: WalletListItem) : Item<GroupieViewHolder>() {
        override fun getLayout() = R.layout.list_item_wallet

        override fun bind(viewHolder: GroupieViewHolder, position: Int): Unit =
            with(viewHolder.itemView) {
                walletNameTextView.text = wallet.walletName
                balanceTextView.text = wallet.balance

                GlideToVectorYou
                    .init()
                    .with(context)
                    .load(Uri.parse(wallet.logo), walletPictureImageView)

                walletMetalTextView.text = wallet.metalName ?: ""
            }
    }
}
